package com.example.umc4_delivery_people.menu;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.member.dto.PostMemberReq;
import com.example.umc4_delivery_people.menu.dto.GetMenuReq;
import com.example.umc4_delivery_people.menu.dto.GetMenuRes;
import com.example.umc4_delivery_people.menu.dto.PostMenuReq;
import com.example.umc4_delivery_people.menu.dto.PostMenuRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
public class MenuController {
    private final MenuService menuService;

    /**
     * 메뉴 생성
     */
    @PostMapping("/create")
    public BaseResponse<PostMenuRes> createMenu(@RequestBody PostMenuReq postMenuReq) {
        try {
            return new BaseResponse<>(menuService.createMenu(postMenuReq));
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 해당 가게에 대한 메뉴 출력
     */
    @GetMapping("/search")
    public BaseResponse<List<GetMenuRes>> getMenus(@RequestBody GetMenuReq getMenuReq) {
        try {
            return new BaseResponse<>(menuService.getMenusByStore(getMenuReq.getName()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
