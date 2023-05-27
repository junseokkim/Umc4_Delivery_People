package com.example.umc4_delivery_people.order_info;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.order_info.dto.GetOrderInfoReq;
import com.example.umc4_delivery_people.order_info.dto.GetOrderInfoRes;
import com.example.umc4_delivery_people.order_info.dto.PostOrderInfoReq;
import com.example.umc4_delivery_people.order_info.dto.PostOrderInfoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("order/info")
public class OrderInfoController {
    private final OrderInfoService orderInfoService;

    /**
     * 주문 정보 생성
     **/
    @PostMapping("/create")
    public BaseResponse<PostOrderInfoRes> createOrderInfo(@RequestBody PostOrderInfoReq postOrderInfoReq) {
        try {
            return new BaseResponse<>(orderInfoService.createOrderInfo(postOrderInfoReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 특정 사용자 주문 내역 조회
     */
    @GetMapping("/search")
    public BaseResponse<List<GetOrderInfoRes>> searchOrderInfo(@RequestBody GetOrderInfoReq getOrderInfoReq) {
        try {
            return new BaseResponse<>(orderInfoService.searchOrderInfo(getOrderInfoReq.getMemberId()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
