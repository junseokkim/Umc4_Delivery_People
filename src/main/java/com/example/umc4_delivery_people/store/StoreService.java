package com.example.umc4_delivery_people.store;

import com.example.umc4_delivery_people.config.BaseException;
import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.store.dto.GetStoreRes;
import com.example.umc4_delivery_people.store.dto.PostStoreReq;
import com.example.umc4_delivery_people.store.dto.PostStoreRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    /**
     * 가게 생성 후 DB에 저장
     */
    public PostStoreRes createStore(PostStoreReq postStoreReq) throws BaseException {
        // 사용된 가게 이름인지 확인
        if(storeRepository.countByName(postStoreReq.getName()) >= 1)
            throw new BaseException(BaseResponseStatus.POST_STORES_EXISTS_NAME);
        try {
            Store store = new Store();
            store.createStore(postStoreReq.getName(), postStoreReq.getContent(), postStoreReq.getMinOrderAmount(), postStoreReq.getMinDeliveryTime(), postStoreReq.getMaxDeliveryTime(), postStoreReq.getDeliveryTip());
            storeRepository.save(store);
            return new PostStoreRes(store.getId(), store.getName());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    /**
     * 특정 가게 이름 조회
     */
    public GetStoreRes getStoreByName(String name) throws BaseException {
        Store store = storeRepository.findByName(name);
        // 존재하지 않는 가게명일 경우 예외처리
        if(store == null) throw new BaseException(BaseResponseStatus.GET_STORES_NOEXISTS_NAME);
        try {
            GetStoreRes getStoreRes = new GetStoreRes(store.getId(),store.getName(), store.getContent(), store.getMinOrderAmount(),
                    store.getMinDeliveryTime(), store.getMaxDeliveryTime(), store.getDeliveryTip());
            return getStoreRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
