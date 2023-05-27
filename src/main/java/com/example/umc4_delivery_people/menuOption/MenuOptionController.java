package com.example.umc4_delivery_people.menuOption;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.menuOption.dto.PostMenuOptionReq;
import com.example.umc4_delivery_people.menuOption.dto.PostMenuOptionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("menu/option")
public class MenuOptionController {
    private final MenuOptionService menuOptionService;

    /**
     * 메뉴옵션 생성
     */
    @PostMapping("/create")
    public BaseResponse<PostMenuOptionRes> createMenuOption(@RequestBody PostMenuOptionReq postMenuOptionReq) {
        try {
            return new BaseResponse<>(menuOptionService.createMenuOption(postMenuOptionReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
