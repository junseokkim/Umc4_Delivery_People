package com.example.umc4_delivery_people.store_type;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.store.StoreRepository;
import com.example.umc4_delivery_people.store_type.dto.GetStoreTypeRes;
import com.example.umc4_delivery_people.store_type.dto.PostStoreTypeReq;
import com.example.umc4_delivery_people.store_type.dto.PostStoreTypeRes;
import com.example.umc4_delivery_people.store_type.type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreTypeService {
    private final StoreTypeRepository storeTypeRepository;
    private final StoreRepository storeRepository;

    /**
     * 가게_타입 생성 후 DB에 저장
     */
    public PostStoreTypeRes createStoreType(PostStoreTypeReq postStoreTypeReq) throws BaseException {
        try {
            StoreType storeType = new StoreType();
            Store store = storeRepository.findById(postStoreTypeReq.getStoreId()).orElseThrow();
            storeType.createStoreType(postStoreTypeReq.getType(), store);
            storeTypeRepository.save(storeType);
            return new PostStoreTypeRes(storeType.getId());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 특정 타입 가게 조회
     */
    public List<GetStoreTypeRes> getStoresByType(type type) throws BaseException {
        List<Store> stores = storeTypeRepository.findStoresByType(type);
        if(stores.isEmpty()) throw new BaseException(BaseResponseStatus.GET_STORES_NOEXISTS_TYPE);
        try {
            return stores.stream()
                    .map(store -> new GetStoreTypeRes(store.getId(), store.getName()))
                    .toList();
        } catch (Exception e) {
            throw new BaseException((BaseResponseStatus.DATABASE_ERROR));
        }
    }


}
