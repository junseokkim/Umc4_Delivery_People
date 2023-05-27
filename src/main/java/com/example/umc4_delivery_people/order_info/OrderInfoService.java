package com.example.umc4_delivery_people.order_info;

import com.example.umc4_delivery_people.basket.Basket;
import com.example.umc4_delivery_people.basket.BasketRepository;
import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.order_info.dto.GetOrderInfoReq;
import com.example.umc4_delivery_people.order_info.dto.GetOrderInfoRes;
import com.example.umc4_delivery_people.order_info.dto.PostOrderInfoReq;
import com.example.umc4_delivery_people.order_info.dto.PostOrderInfoRes;
import com.example.umc4_delivery_people.order_menu.OrderMenu;
import com.example.umc4_delivery_people.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderInfoService {
    private final OrderInfoRepository orderInfoRepository;
    private final BasketRepository basketRepository;

    /**
     * 주문 정보 생성 후 DB 추가
     */
    public PostOrderInfoRes createOrderInfo(PostOrderInfoReq postOrderInfoReq) throws BaseException {
        try {
            Basket basket = basketRepository.getReferenceById(postOrderInfoReq.getBasketId());
            // 메뉴 및 옵션 총 가격과 배달팁을 통해 총 금액 계산
            Integer totalPrice = basket.getMenuPrice() + basket.getDeliveryTip();
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.createOrderInfo(postOrderInfoReq.getWaitingTime(), totalPrice, postOrderInfoReq.getRequestStore(), postOrderInfoReq.getRequestDeliver(), basket);
            orderInfoRepository.save(orderInfo);
            return new PostOrderInfoRes(orderInfo.getId(), basket.getMember().getNickName());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 특정 사용자 주문 내역 DB에서 조회
     */
    public List<GetOrderInfoRes> searchOrderInfo(Long memberId) throws BaseException {
        try {
            // DB에서 해당 멤버의 주문내역 받아오기
            List<OrderInfo> orderInfos = orderInfoRepository.findOrderInfoByMemberId(memberId);
            List<GetOrderInfoRes> getOrderInfoRes = new ArrayList<>();
            // 받아온 주문내역 Response 리스트로 변경해주기
            for(OrderInfo orderInfo : orderInfos) {
                Basket basket = orderInfo.getBasket();
                Store store = basket.getStore();
                List<OrderMenu> orderMenus = basket.getOrderMenus();
                String menuName;
                // 메뉴가 없는 주문내역일 경우 빈 문자열 보내기
                if(orderMenus.size() == 0) menuName = "";
                // 첫번째 메뉴 이름 받기
                else menuName = orderMenus.get(0).getMenu().getName();
                GetOrderInfoRes tmp = new GetOrderInfoRes(orderInfo.getId(), orderInfo.getCreateDate(), store.getName(), menuName, orderInfo.getTotalPrice());
                getOrderInfoRes.add(tmp);
            }
            return getOrderInfoRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
