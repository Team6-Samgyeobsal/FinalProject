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

    /**
     * 명예의 전당 리스트를 리턴
     * @param criteria : 명예의 전당 객체
     */
    @Override
    public List<FameVO> getFameList(FameCriteria criteria) {
        return fameMapper.getFameList(criteria);
    }
}
