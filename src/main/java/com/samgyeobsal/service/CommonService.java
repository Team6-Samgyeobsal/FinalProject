package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;

import java.util.List;

public interface CommonService {

    List<CompetitionHyundaiVO> getActiveCompetitionList();

    CompetitionHyundaiVO getCompetitionByCidAndTid(String cid, String tid);
}
