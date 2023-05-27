package com.example.umc4_delivery_people.basket;

import com.example.umc4_delivery_people.basket.dto.PostBasketReq;
import com.example.umc4_delivery_people.basket.dto.PostBasketRes;
import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
