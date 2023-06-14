package com.example.umc4_delivery_people.basket;

import com.example.umc4_delivery_people.basket.dto.GetBasketRes;
import com.example.umc4_delivery_people.basket.dto.PostBasketReq;
import com.example.umc4_delivery_people.basket.dto.PostBasketRes;
import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("basket")
public class BasketController {
    private final BasketService basketService;

    /**
     * 장바구니 생성
     */
    @PostMapping("/create")
    public BaseResponse<PostBasketRes> createBasket(@RequestBody PostBasketReq postBasketReq){
        try {
            return new BaseResponse<>(basketService.createBasket(postBasketReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 현재 사용자의 현재 장바구니 조회
     */
    @GetMapping("/search/{memberId}")
    public BaseResponse<GetBasketRes> searchBasket(@PathVariable("memberId") Long memberId) {
        try {
            // Long tmp = Long.parseLong(memberId);
            return new BaseResponse<>(basketService.searchBasket(memberId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
