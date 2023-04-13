package com.samgyeobsal.service;


import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.domain.store.StoreVO;
import com.samgyeobsal.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{


    private final StoreMapper storeMapper;

    /**
     * 더 현대에 입점한 스토어 리스트 리턴
     */
    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

}
