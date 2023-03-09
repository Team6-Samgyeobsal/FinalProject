package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonMapper commonMapper;
    @Override
    public List<CompetitionHyundaiVO> getActiveCompetitionList() {
        return commonMapper.getActiveCompetitionList();
    }

    @Override
    public CompetitionHyundaiVO getCompetitionByCidAndTid(String cid, String tid) {
        return commonMapper.getCompetitionByCidAndTid(cid, tid);
    }

}
