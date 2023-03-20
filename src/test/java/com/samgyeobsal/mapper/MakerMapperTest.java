package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.funding.ProdOptionVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class MakerMapperTest {

    @Autowired
    private MakerMapper makerMapper;

    @Test
    @Transactional
    void insertFunding(){
        String fid = UUID.randomUUID().toString();
        String memail = "user@gmail.com";
        makerMapper.insertFunding(fid, memail);
    }

    @Test
    void findFundingMakerByFundingId(){
        String fundingId = "1";
        FundingMakerVO fundingMaker = makerMapper.findFundingMakerByFundingId(fundingId);
        log.info("fundingMaker = {}", fundingMaker);
    }
    @Test
    @Transactional
    void updateFundingBaseInfo(){
        String thumb = "testThumb";
        FundingBaseInfoDTO baseInfo = new FundingBaseInfoDTO();
        baseInfo.setFstore_name("storeName");
        baseInfo.setFid("1");
        baseInfo.setFthumb(thumb);
        baseInfo.setCtid("2");
        baseInfo.setCid("session2");

        makerMapper.updateFundingBaseInfo(baseInfo);

        FundingMakerVO fundingMaker = makerMapper.findFundingMakerByFundingId("1");
        Assertions.assertEquals(thumb, fundingMaker.getFthumb());
    }

    @Test
    void findFundingImgByFundingId(){
        String fundingId = "111";
        List<FundingImgVO> fundingImg = makerMapper.findFundingImgListByFundingId(fundingId);
        log.info("fundingImg = {}", fundingImg);
    }

    @Test
    @Transactional
    void updateFundingStory(){
        FundingStoryDTO story = new FundingStoryDTO();
        story.setFid("1");
        story.setFtitle("testTitle");
        story.setFsummary("testSummary");
        story.setFstory("testStory");
        makerMapper.updateFundingStory(story);

        FundingMakerVO fundingMaker = makerMapper.findFundingMakerByFundingId("1");
        Assertions.assertEquals(fundingMaker.getFtitle(), story.getFtitle());
    }

    @Test
    @Transactional
    void deleteFundingImgsByFundingId(){
        String fid = "1";
        makerMapper.deleteFundingImgsByFundingId(fid);
        List<FundingImgVO> fundingImgs = makerMapper.findFundingImgListByFundingId(fid);
        Assertions.assertEquals(fundingImgs.size(), 0);
    }

    @Test
    @Transactional
    void insertFundingImgs(){
        String fid = "1";
        FundingImgVO img1 = new FundingImgVO();
        img1.setFid(fid);
        img1.setFiid(UUID.randomUUID().toString());
        img1.setFiurl("http://localhost/displayImg?imgName=123123123123");

        FundingImgVO img2 = new FundingImgVO();
        img2.setFid(fid);
        img2.setFiid(UUID.randomUUID().toString());
        img2.setFiurl("http://localhost/displayImg?imgName=123123123123");
        List<FundingImgVO> list = new ArrayList<>();
        list.add(img1);
        list.add(img2);
        int row = makerMapper.insertFundingImgs(list);
        Assertions.assertEquals(row, list.size());

    }

    @Test
    @Transactional
    void insertFundingProduct(){
        String fpid = UUID.randomUUID().toString();
        String fid = "1";
        UpdateFundingProductDTO product = new UpdateFundingProductDTO();
        product.setFpid(fpid);
        product.setFid(fid);

        product.setOriginPrice("1000");
        product.setPrice("900");
        product.setContent("testContent");
        product.setTitle("testTitle");

        int row = makerMapper.insertFundingProduct(product);
        Assertions.assertEquals(row, 1);
    }

    @Test
    @Transactional
    void updateFundingProduct(){
        String fpid = "1";
        String fid = "1";
        UpdateFundingProductDTO product = new UpdateFundingProductDTO();
        product.setFpid(fpid);
        product.setFid(fid);

        product.setOriginPrice("1000");
        product.setPrice("900");
        product.setContent("testContent");
        product.setTitle("testTitle");

        int row = makerMapper.updateFundingProduct(product);
        Assertions.assertEquals(row,1);
    }

    @Test
    @Transactional
    void deleteAllFundingProductOption(){
        String fpid = "1";
        makerMapper.deleteAllFundingProductOption(fpid);
    }

    @Test
    @Transactional
    void updateProductOption(){
        String fpid = "1";
        ProdOptionVO opt = new ProdOptionVO();
        opt.setFpid(fpid);
        opt.setPoid("1");
        opt.setPooption("testOption");

    }

}
