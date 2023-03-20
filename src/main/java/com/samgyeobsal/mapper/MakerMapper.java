package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.funding.ProdOptionVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MakerMapper {

    FundingMakerVO findFundingMakerByFundingId(String fundingId);

    int insertFunding(String fid, String memail);

    int updateFundingBaseInfo(FundingBaseInfoDTO baseInfo);

    List<FundingImgVO> findFundingImgListByFundingId(String fundingId);

    int updateFundingStory(FundingStoryDTO story);

    int deleteFundingImgsByFundingId(String fid);


    int insertFundingImgs(@Param("imgs") List<FundingImgVO> imgs);

    int insertFundingProduct(UpdateFundingProductDTO product);

    int updateFundingProduct(UpdateFundingProductDTO product);

    void deleteAllFundingProductOption(String fpid);

    void updateProductOption(ProdOptionVO opt);

    int deleteFundingProduct(String fpid);


}
