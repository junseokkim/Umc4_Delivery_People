package com.example.umc4_delivery_people.store;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.store.dto.GetStoreReq;
import com.example.umc4_delivery_people.store.dto.GetStoreRes;
import com.example.umc4_delivery_people.store.dto.PostStoreReq;
import com.example.umc4_delivery_people.store.dto.PostStoreRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("store")
public class StoreController {
    private final StoreService storeService;

    /**
     * 가게 생성
     */
    @PostMapping("/create")
    public BaseResponse<PostStoreRes> createStore(@RequestBody PostStoreReq postStoreReq) {
        try {
            return new BaseResponse<>(storeService.createStore(postStoreReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 가게 이름으로 가게 찾기
     */
    @GetMapping("/search")
    public BaseResponse<GetStoreRes> searchStore(@RequestBody GetStoreReq getStoreReq) {
        try {
            return new BaseResponse<>(storeService.getStoreByName(getStoreReq.getName()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
