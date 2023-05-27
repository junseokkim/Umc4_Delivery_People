package com.example.umc4_delivery_people.order_menu;

import com.example.umc4_delivery_people.basket.Basket;
import com.example.umc4_delivery_people.basket.BasketRepository;
import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.menu.Menu;
import com.example.umc4_delivery_people.menu.MenuRepository;
import com.example.umc4_delivery_people.order_menu.dto.PatchOrderMenuReq;
import com.example.umc4_delivery_people.order_menu.dto.PatchOrderMenuRes;
import com.example.umc4_delivery_people.order_menu.dto.PostOrderMenuReq;
import com.example.umc4_delivery_people.order_menu.dto.PostOrderMenuRes;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderMenuService {
    private final OrderMenuRepository orderMenuRepository;
    private final MenuRepository menuRepository;
    private final BasketRepository basketRepository;

    /**
     * 주문메뉴 생성 후 DB에 저장
     */
    public PostOrderMenuRes createOrderMenu(PostOrderMenuReq postOrderMenuReq) throws BaseException {
        try {
            Menu menu = menuRepository.getReferenceById(postOrderMenuReq.getMenuId());
            Basket basket = basketRepository.getReferenceById(postOrderMenuReq.getBasketId());
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.createOrderMenu(menu.getPrice(), menu, basket);
            orderMenuRepository.save(orderMenu);
            return new PostOrderMenuRes(orderMenu.getId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 주문메뉴 상태 변경 후 DB에 수정
     */
    @Transactional
    public PatchOrderMenuRes removeOrderMenu(Long orderMenuId) throws BaseException {
        try {
            OrderMenu orderMenu = orderMenuRepository.getReferenceById(orderMenuId);
            orderMenu.updateStatus();
            return new PatchOrderMenuRes(orderMenu.getId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
