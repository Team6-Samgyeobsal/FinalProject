package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.mapper.FundingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundingServiceImpl implements FundingService{

    @Autowired
    private FundingMapper fundingMapper;

    public List<FundingVO> getFundingList(FundingCriteria criteria){
        return fundingMapper.getFundingList(criteria);
    }

}
