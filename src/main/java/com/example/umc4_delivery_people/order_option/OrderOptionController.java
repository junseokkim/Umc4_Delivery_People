package com.example.umc4_delivery_people.order_option;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.order_option.dto.PostOrderOptionReq;
import com.example.umc4_delivery_people.order_option.dto.PostOrderOptionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("order/option")
public class OrderOptionController {
    private final OrderOptionService orderOptionService;

    /**
     * 주문메뉴옵션 생성
     */
    @PostMapping("/create")
    public BaseResponse<PostOrderOptionRes> createOrderOption(@RequestBody PostOrderOptionReq postOrderOptionReq) {
        try {
            return new BaseResponse<>(orderOptionService.createOrderOption(postOrderOptionReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
