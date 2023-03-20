package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.CategoryVO;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonMapper commonMapper;

    @Value("${review.sentimental.url}")
    private String reqUrl;

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
