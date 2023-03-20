package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.domain.store.StoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<StoreVO> getStoreList();

}
