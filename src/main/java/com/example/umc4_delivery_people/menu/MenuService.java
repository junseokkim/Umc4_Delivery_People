package com.example.umc4_delivery_people.menu;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.member.dto.PostMemberReq;
import com.example.umc4_delivery_people.menu.dto.GetMenuReq;
import com.example.umc4_delivery_people.menu.dto.GetMenuRes;
import com.example.umc4_delivery_people.menu.dto.PostMenuReq;
import com.example.umc4_delivery_people.menu.dto.PostMenuRes;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    /**
     * 메뉴 생성 후 같은 가게에서 메뉴명 중복 검사 후 DB에 저장
     */
    public PostMenuRes createMenu(PostMenuReq postMenuReq) throws BaseException {
        // 중복 검사
        Store store = storeRepository.getReferenceById(postMenuReq.getStoreId());
        if(menuRepository.countByStoreAndName(store, postMenuReq.getName()) >= 1)
            throw new BaseException(BaseResponseStatus.POST_MENUS_EXISTS_NAME);
        try {
            Menu menu = new Menu();
            menu.createMenu(postMenuReq.getName(), postMenuReq.getIngredient(), postMenuReq.getPrice(), store);
            menuRepository.save(menu);
            return new PostMenuRes(menu.getId(), menu.getName());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 특정 가게에 대한 메뉴 조회
     */
    public List<GetMenuRes> getMenusByStore(String name) throws BaseException {
        try {
            Long storeId = storeRepository.findByName(name).getId();
            List<Menu> menus = menuRepository.findByStore(storeId);
            List<GetMenuRes> getMenuRes = menus.stream()
                    .map(menu -> new GetMenuRes(menu.getId(), menu.getName(), menu.getIngredient()))
                    .collect(Collectors.toList());
            return getMenuRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
