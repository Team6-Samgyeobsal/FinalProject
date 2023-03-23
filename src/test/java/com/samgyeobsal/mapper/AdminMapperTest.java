package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import com.samgyeobsal.domain.funding.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void getDocumentList(){
        List<FundingDocumentDTO> documentList = adminMapper.getDocumentList();
        for (FundingDocumentDTO document : documentList) {
            log.info("document = {}", document);

        }
    }

    @Transactional
    @Test
    void updateDocumentStatus(){
        String fid = "d5c1a8a5-6466-4488-8792-66e39e26ff1e";
        UpdateDocumentDTO document = new UpdateDocumentDTO();
        document.setFid(fid);
        document.setIsPass(true);

        int i = adminMapper.updateDocumentStatus(document);
        log.info("row = {}", i);
    }

    @Test
    void getAllReviewList(){
        List<ReviewVO> allReviewList = adminMapper.getAllReviewList();
        for (ReviewVO reviewVO : allReviewList) {
            log.info("review = {}", reviewVO);

        }
    }
}
