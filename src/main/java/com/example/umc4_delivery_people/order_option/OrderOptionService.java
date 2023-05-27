package com.example.umc4_delivery_people.order_option;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.menuOption.MenuOption;
import com.example.umc4_delivery_people.menuOption.MenuOptionRepository;
import com.example.umc4_delivery_people.order_menu.OrderMenu;
import com.example.umc4_delivery_people.order_menu.OrderMenuRepository;
import com.example.umc4_delivery_people.order_option.dto.PostOrderOptionReq;
import com.example.umc4_delivery_people.order_option.dto.PostOrderOptionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderOptionService {
    private final OrderOptionRepository orderOptionRepository;
    private final MenuOptionRepository menuOptionRepository;
    private final OrderMenuRepository orderMenuRepository;

    /**
     * 주문메뉴옵션 생성 후 DB 추가
     */
    public PostOrderOptionRes createOrderOption(PostOrderOptionReq postOrderOptionReq) throws BaseException {
        try {
            OrderMenu orderMenu = orderMenuRepository.getReferenceById(postOrderOptionReq.getOrderMenuId());
            MenuOption menuOption = menuOptionRepository.getReferenceById(postOrderOptionReq.getMenuOptionId());
            OrderOption orderOption = new OrderOption();
            orderOption.createOrderOption(menuOption, orderMenu);
            orderOptionRepository.save(orderOption);
            return new PostOrderOptionRes(orderOption.getId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
