package com.samgyeobsal.service;

import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.mapper.MakerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakerServiceImpl implements MakerService {

    private final MakerMapper makerMapper;

    @Override
    public FundingMakerVO getFundingMakerByFundingId(String fundingId) {
        return makerMapper.findFundingMakerByFundingId(fundingId);
    }
}
