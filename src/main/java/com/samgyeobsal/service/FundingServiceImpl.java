package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.mapper.FundingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundingServiceImpl {

    @Autowired
    FundingMapper fundingMapper;

    FundingVO getFundingList(){
        return fundingMapper.getFundingList();
    }

}
