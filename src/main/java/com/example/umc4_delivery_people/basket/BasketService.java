package com.example.umc4_delivery_people.basket;

import com.example.umc4_delivery_people.basket.dto.GetBasketRes;
import com.example.umc4_delivery_people.basket.dto.PostBasketReq;
import com.example.umc4_delivery_people.basket.dto.PostBasketRes;
import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.member.Member;
import com.example.umc4_delivery_people.member.MemberRepository;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

@RequiredArgsConstructor
@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    /**
     * 장바구니 생성 후 DB에 저장
     */
    public PostBasketRes createBasket(PostBasketReq postBasketReq) throws BaseException {
        Store store = storeRepository.getReferenceById(postBasketReq.getStoreId());
        Member member = memberRepository.getReferenceById(postBasketReq.getMemberId());
        // 해당 사용자의 현재 장바구니가 존재하는 지 확인 후 있다면 상태 변경
        if(basketRepository.countByMemberAndStatus(member, true) == 1) {
            Basket basket = basketRepository.findBasketByMemberAndStatus(member, true);
            basket.updateStatus();
        }
        try {
            Basket basket = new Basket();
            basket.createBasket(store.getDeliveryTip(), postBasketReq.getDeliveryType(),member , store);
            basketRepository.save(basket);
            return new PostBasketRes(basket.getId(), basket.isStatus());
        } catch (Exception e) { // DB에 이상이 있는 경우 에러 메시지를 보냄
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * DB에서 장바구니 조회
     */
    public GetBasketRes searchBasket(Long memberId) throws BaseException {
        Member member = memberRepository.getReferenceById(memberId);
        if(basketRepository.countByMemberAndStatus(member, true) == 1) {
            Basket basket = basketRepository.findBasketByMember(member);
            return new GetBasketRes(basket.getId(), member.getNickName(), basket.getStore().getName());
        } else {
            throw new BaseException(BaseResponseStatus.GET_BASKET_NOEXISTS);
        }

    }

}
