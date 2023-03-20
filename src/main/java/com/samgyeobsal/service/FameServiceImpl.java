package com.samgyeobsal.service;


import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.mapper.FameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FameServiceImpl implements FameService{
    @Autowired
    FameMapper fameMapper;

    @Override
    public List<FameVO> getFameList(FameCriteria criteria) {
        return fameMapper.getFameList(criteria);
    }
}
