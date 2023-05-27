package com.example.umc4_delivery_people.member;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.member.dto.*;
import com.example.umc4_delivery_people.utils.AES128;
import com.example.umc4_delivery_people.utils.JwtService;
import com.example.umc4_delivery_people.utils.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.umc4_delivery_people.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    /**
     * 유저 생성 후 DB에 저장
     */
    public PostMemberRes createMember(PostMemberReq postMemberReq) throws BaseException {
        // 사용된 이메일인지 확인
        if(memberRepository.countByEmail(postMemberReq.getEmail()) >= 1)
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        String pwd;
        try {
            // 암호화 : postUserReq에서 제공받은 비밀번호를 보안을 위해 암호화시켜 DB에 저장
            // ex) password123 -> dfhsjfkjdsnj4@!$!@chdsnjfwkenjfnsjfnjsd.fdsfaifsadjfjaf
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postMemberReq.getPassword()); // 암호화 코드
        } catch (Exception e) { // 암호화가 실패하였을 경우 에러 발생
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try {
            Member member = new Member();
            member.createMember(postMemberReq.getEmail(), pwd, postMemberReq.getName(), postMemberReq.getNickName(), postMemberReq.getPhoneNumber());
            memberRepository.save(member);
            return new PostMemberRes(member.getId(), member.getNickName());
        } catch (Exception e) { // DB에 이상이 있는 경우 에러 메시지를 보냄
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 유저 로그인
     */
    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
        Member member = memberRepository.findByEmail(postLoginReq.getEmail());
        String password;
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(member.getPassword()); // 암호화
        } catch (Exception e) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
        if(postLoginReq.getPassword().equals(password)) {
            String jwt = jwtService.createJwt(member.getId());
            return new PostLoginRes(member.getId(), jwt);
        }
        else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    /**
     * 모든 회원 조회
     */
    public List<GetMemberRes> getMembers() throws BaseException {
        try{
            List<Member> members = memberRepository.findMembers(); //Member List로 받아 GetMemberRes로 바꿔줌
            List<GetMemberRes> GetMemberRes = members.stream()
                    .map(member -> new GetMemberRes(member.getId(), member.getEmail(), member.getPassword(), member.getName(), member.getNickName(), member.getPhoneNumber(), member.getMrank()))
                    .collect(Collectors.toList());
            return GetMemberRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 특정 닉네임 조회
     */
    public List<GetMemberRes> getMembersByNickName(String nickname) throws BaseException {
        try{
            List<Member> members = memberRepository.findMemberByNickName(nickname);
            List<GetMemberRes> GetMemberRes = members.stream()
                    .map(member -> new GetMemberRes(member.getId(), member.getEmail(), member.getPassword(), member.getName(), member.getNickName(), member.getPhoneNumber(), member.getMrank()))
                    .collect(Collectors.toList());
            return GetMemberRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 닉네임 변경
     */
    @Transactional
    public void modifyUserName(PatchMemberReq patchMemberReq) {
        Member member = memberRepository.getReferenceById(patchMemberReq.getMemberId());
        member.updateNickName(patchMemberReq.getNickName());
    }
}