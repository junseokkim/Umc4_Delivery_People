package com.example.umc4_delivery_people.store_category;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.store.StoreRepository;
import com.example.umc4_delivery_people.store_category.dto.GetStoreCategoryRes;
import com.example.umc4_delivery_people.store_category.dto.PostStoreCategoryReq;
import com.example.umc4_delivery_people.store_category.dto.PostStoreCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreCategoryService {
    private final StoreCategoryRepository storeCategoryRepository;
    private final StoreRepository storeRepository;

    /**
     * 가게_카테고리 생성 후 DB에 저장
     */
    public PostStoreCategoryRes createStoreCategory(PostStoreCategoryReq postStoreCategoryReq) throws BaseException {
        // 이미 존재하는 가게_카테고리인지 확인하는 코드 추가하기
        try {
            StoreCategory storeCategory = new StoreCategory();
            Store store = storeRepository.findById(postStoreCategoryReq.getStoreId()).orElseThrow();
            storeCategory.createStoreCategory(postStoreCategoryReq.getCategory(), store);
            storeCategoryRepository.save(storeCategory);
            return new PostStoreCategoryRes(storeCategory.getId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 특정 카테고리 가게 조회
     */
    public List<GetStoreCategoryRes> getStoresByCategory(category category) throws BaseException {
        List<Store> stores = storeCategoryRepository.findStoresByCategory(category);
        if(stores.isEmpty()) throw new BaseException(BaseResponseStatus.GET_STORES_NOEXISTS_CATEGORY);
        try {
            return stores.stream()
                    .map(store -> new GetStoreCategoryRes(store.getId(), store.getName()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
