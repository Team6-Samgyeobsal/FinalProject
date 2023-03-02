package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;

import java.sql.SQLException;
import java.util.List;

public interface FundingService {
    List<FundingVO> getFundingList(FundingCriteria criteria) ;
}
