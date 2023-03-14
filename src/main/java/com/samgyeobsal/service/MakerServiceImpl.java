package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.funding.ProdOptionVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import com.samgyeobsal.mapper.MakerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakerServiceImpl implements MakerService {

    private final MakerMapper makerMapper;

    @Override
    public FundingMakerVO getFundingMakerByFundingId(String fundingId) {
        return makerMapper.findFundingMakerByFundingId(fundingId);
    }

    @Override
    public void updateFundingBaseInfo(FundingBaseInfoDTO baseInfo) {
        int row = makerMapper.updateFundingBaseInfo(baseInfo);
        if(row == 0) throw new RuntimeException("에러 발생");
    }

    @Override
    public List<FundingImgVO> getFundingImgsByFundingId(String fundingId) {
        List<FundingImgVO> fundingImgs = makerMapper.findFundingImgListByFundingId(fundingId);
        return fundingImgs == null ? new ArrayList<>() : fundingImgs;
    }

    @Override
    public void updateFundingStory(FundingStoryDTO story) {
        int row = makerMapper.updateFundingStory(story);
        makerMapper.deleteFundingImgsByFundingId(story.getFid());

        // null 인 이미지 객체 필터링
        List<FundingImgVO> filteredImgs = story.getImgs()
                .stream().filter(i -> i.getFid() != null && i.getFiid() != null && i.getFiurl() != null).collect(Collectors.toList());
        if(filteredImgs.size() == 0) throw new RuntimeException("이미지 없음");
        makerMapper.insertFundingImgs(filteredImgs);

        if(row == 0) throw new RuntimeException("에러 발생");
    }

    @Override
    public void updateFundingProduct(UpdateFundingProductDTO product) {
        int row = 0;
        if(product.getFpid() == null || product.getFpid().equals("")){
            product.setFpid(UUID.randomUUID().toString());
            row = makerMapper.insertFundingProduct(product);
        }else{
            row = makerMapper.updateFundingProduct(product);
        }
        // option 전부 status = 0 으로
        makerMapper.deleteAllFundingProductOption(product.getFpid());

        for (ProdOptionVO opt : product.getOpts()) {
            opt.setFpid(product.getFpid());
            if(opt.getPoid() == null || opt.getPoid().equals("")) opt.setPoid(UUID.randomUUID().toString());
            makerMapper.updateProductOption(opt);
        }

        if(row == 0) throw new RuntimeException("updateFundingProduct 에러 발생");

    }

    @Override
    public void deleteFundingProduct(String fpid) {
        int row = makerMapper.deleteFundingProduct(fpid);
        if(row == 0) throw new RuntimeException("deleteFundingProduct 에러발생");
    }
}
