package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.CategoryVO;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.domain.order.OrderVO;

import java.util.List;

public interface CommonService {

    List<CompetitionHyundaiVO> getActiveCompetitionList();

    CompetitionHyundaiVO getCompetitionByCidAndTid(String cid, String tid);

    List<CategoryVO> getCategoryList();

    int getReviewScore(String review);

    void sendOrderInfoByKakaoMessageApi(String memail, OrderVO orderVO);



}
