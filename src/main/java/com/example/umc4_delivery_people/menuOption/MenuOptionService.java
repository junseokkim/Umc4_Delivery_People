package com.example.umc4_delivery_people.menuOption;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.menu.Menu;
import com.example.umc4_delivery_people.menu.MenuRepository;
import com.example.umc4_delivery_people.menuOption.dto.PostMenuOptionReq;
import com.example.umc4_delivery_people.menuOption.dto.PostMenuOptionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuOptionService {
    private final MenuOptionRepository menuOptionRepository;
    private final MenuRepository menuRepository;

    /**
     * 메뉴 옵션 생성 후 DB에 추가
     */
    public PostMenuOptionRes createMenuOption(PostMenuOptionReq postMenuOptionReq) throws BaseException {
        try {
            MenuOption menuOption = new MenuOption();
            Menu menu = menuRepository.getReferenceById(postMenuOptionReq.getMenuId());
            menuOption.createMenuOption(postMenuOptionReq.getName(), postMenuOptionReq.getPrice(), menu);
            menuOptionRepository.save(menuOption);
            return new PostMenuOptionRes(menuOption.getId());
        } catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

}
