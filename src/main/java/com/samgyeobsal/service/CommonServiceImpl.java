package com.samgyeobsal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.common.CategoryVO;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.order.OrderItemVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.CommonMapper;
import com.samgyeobsal.mapper.QrCodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonMapper commonMapper;

    @Value("${review.sentimental.url}")
    private String reqUrl;

    @Value("${kakao.api.url.friends}")
    private String friendsApiUrl;

    @Value("${kakao.api.url.message}")
    private String messageApiUrl;

    @Override
    public List<CompetitionHyundaiVO> getActiveCompetitionList() {
        return commonMapper.getActiveCompetitionList();
    }

    @Override
    public CompetitionHyundaiVO getCompetitionByCidAndTid(String cid, String tid) {
        return commonMapper.getCompetitionByCidAndTid(cid, tid);
    }

    @Override
    public List<CategoryVO> getCategoryList() {
        return commonMapper.getCategoryList();
    }

    @Override
    public int getReviewScore(String review) {
        return sendReviewScoreRequest(review);
    }

    private int sendReviewScoreRequest(String review){
        int score = 3;
        try{
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(reqUrl+"?review="+review, String.class);
            return Integer.parseInt(response);
        }catch (Exception e){
            log.info("error ",e);
            return score;
        }
    }

}
