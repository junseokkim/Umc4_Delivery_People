package com.example.umc4_delivery_people.store_category;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponse;
import com.example.umc4_delivery_people.store.dto.GetStoreRes;
import com.example.umc4_delivery_people.store_category.dto.GetStoreCategoryReq;
import com.example.umc4_delivery_people.store_category.dto.GetStoreCategoryRes;
import com.example.umc4_delivery_people.store_category.dto.PostStoreCategoryReq;
import com.example.umc4_delivery_people.store_category.dto.PostStoreCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/category")
public class StoreCategoryController {
    private final StoreCategoryService storeCategoryService;

    /**
     * 가게_카테고리 정보 추가
     */
    @PostMapping("/create")
    public BaseResponse<PostStoreCategoryRes> createCategory(@RequestBody PostStoreCategoryReq postStoreCategoryReq) {
        try {
            return new BaseResponse<>(storeCategoryService.createStoreCategory(postStoreCategoryReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    /**
     * 카테고리에 따른 가게 찾기
     */
    @GetMapping("/search")
    public BaseResponse<List<GetStoreCategoryRes>> getStores(@RequestBody GetStoreCategoryReq getStoreCategoryReq) {
        try {
            return new BaseResponse<>(storeCategoryService.getStoresByCategory(getStoreCategoryReq.getCategory()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
