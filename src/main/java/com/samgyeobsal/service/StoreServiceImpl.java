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

    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

}
