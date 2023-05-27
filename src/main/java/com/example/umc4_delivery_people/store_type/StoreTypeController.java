package com.example.umc4_delivery_people.store_type;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.store_type.dto.GetStoreTypeReq;
import com.example.umc4_delivery_people.store_type.dto.GetStoreTypeRes;
import com.example.umc4_delivery_people.store_type.dto.PostStoreTypeReq;
import com.example.umc4_delivery_people.store_type.dto.PostStoreTypeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/type")
public class StoreTypeController {
    private final StoreTypeService storeTypeService;

    /**
     * 가게_타입 정보 추가
     */
    @PostMapping("/create")
    public BaseResponse<PostStoreTypeRes> createType(@RequestBody PostStoreTypeReq postStoreTypeReq) {
        try {
            return new BaseResponse<>(storeTypeService.createStoreType(postStoreTypeReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 타입에 따른 가게 찾기
     */
    @GetMapping("/search")
    public BaseResponse<List<GetStoreTypeRes>> getStores(@RequestBody GetStoreTypeReq getStoreTypeReq) {
        try {
            return new BaseResponse<>(storeTypeService.getStoresByType(getStoreTypeReq.getType()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
