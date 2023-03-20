package com.samgyeobsal.service;


import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.domain.store.StoreVO;
import com.samgyeobsal.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreMapper storeMapper;

    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

}
