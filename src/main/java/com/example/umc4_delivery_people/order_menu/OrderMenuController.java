package com.example.umc4_delivery_people.order_menu;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.order_menu.dto.PatchOrderMenuReq;
import com.example.umc4_delivery_people.order_menu.dto.PatchOrderMenuRes;
import com.example.umc4_delivery_people.order_menu.dto.PostOrderMenuReq;
import com.example.umc4_delivery_people.order_menu.dto.PostOrderMenuRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("order/menu")
public class OrderMenuController {
    private final OrderMenuService orderMenuService;

    /**
     * 장바구니에 추가된 주문메뉴 정보 추가
     */
    @PostMapping("/create")
    public BaseResponse<PostOrderMenuRes> createOrderMenu(@RequestBody PostOrderMenuReq postOrderMenuReq) {
        try {
            return new BaseResponse<>(orderMenuService.createOrderMenu(postOrderMenuReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 장바구니에 삭제된 주문메뉴 정보 삭제
     */
    @PatchMapping("/remove")
    public BaseResponse<PatchOrderMenuRes> removeOrderMenu(@RequestParam Long orderMenuId) {
        try {
            return new BaseResponse<>(orderMenuService.removeOrderMenu(orderMenuId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
