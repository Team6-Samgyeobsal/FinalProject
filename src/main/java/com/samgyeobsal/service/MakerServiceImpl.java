package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.funding.ProdOptionVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.exception.DbException;
import com.samgyeobsal.exception.FundingInvalidException;
import com.samgyeobsal.exception.UploadFileException;
import com.samgyeobsal.mapper.AdminMapper;
import com.samgyeobsal.mapper.MakerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakerServiceImpl implements MakerService {

    private final MakerMapper makerMapper;
    private final FundingService fundingService;
    private final AdminMapper adminMapper;

    /**
     * 펀딩을 만든 후 펀딩아이디 리턴
     * @param email : 메이커 이메일
     */
    @Override
    public String createFunding(String email) {
        String fid = UUID.randomUUID().toString();
        int row = makerMapper.insertFunding(fid, email);
        if(row == 0) throw new RuntimeException("funding createFunding error");
        return fid;
    }

    /**
     * 해당 펀딩 만들기 위한 객체 (FundingMaker) 리턴
     * @param fundingId : 펀딩 아이디
     */
    @Override
    public FundingMakerVO getFundingMakerByFundingId(String fundingId) {
        return makerMapper.findFundingMakerByFundingId(fundingId);
    }

    /**
     * 펀딩 기본정보 수정
     * @param baseInfo : 펀딩 기본정보 객체
     */
    @Override
    public void updateFundingBaseInfo(FundingBaseInfoDTO baseInfo) {
        int row = makerMapper.updateFundingBaseInfo(baseInfo);

        if(row == 0) throw new DbException("updateFundingBaseInfo service error");

    }

    /**
     * 해당 펀딩 이미지 리스트 반환
     * @param fundingId : 펀딩 아이디
     */
    @Override
    public List<FundingImgVO> getFundingImgsByFundingId(String fundingId) {
        List<FundingImgVO> fundingImgs = makerMapper.findFundingImgListByFundingId(fundingId);
        return fundingImgs == null ? new ArrayList<>() : fundingImgs;
    }

    /**
     * 펀딩 스토리 수정
     * @param story : 펀딩 스토리 객체
     */
    @Override
    public void updateFundingStory(FundingStoryDTO story) {
        int row = makerMapper.updateFundingStory(story);
        makerMapper.deleteFundingImgsByFundingId(story.getFid());

        // null 인 이미지 객체 필터링
        List<FundingImgVO> filteredImgs = story.getImgs()
                .stream().filter(i -> i.getFid() != null && i.getFiid() != null && i.getFiurl() != null).collect(Collectors.toList());
        log.info("filteredImgs = {}", filteredImgs);

        if(filteredImgs.size() == 0) throw new UploadFileException("no image uploaded");
        makerMapper.insertFundingImgs(filteredImgs);

        if(row == 0) throw new DbException("updateFundingStory error occur");
    }

    /**
     * 펀딩 상품 수정
     * 삭제 시 : postatus = 0
     * @param product : 펀딩 상품 객체
     */
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

        if(row == 0) throw new DbException("updateFundingProduct error occur");

    }

    /**
     * 해당 펀딩 상품 삭제
     * @param fpid : 펀딩 상품 아이디
     */
    @Override
    public void deleteFundingProduct(String fpid) {
        int row = makerMapper.deleteFundingProduct(fpid);
        if(row == 0) throw new DbException("deleteFundingProduct error occur");
    }

    /**
     * 펀딩 상태 변경 (PREPARING -> PARTICIPANT)
     * @param fid : 펀딩 아이디
     */
    @Override
    public void submitDocument(String fid) {
        FundingDetailVO prepare = fundingService.getFundingDetail(fid, "PREPARING");
        log.info("prepare = {}", prepare);

        if(!validateDocument(prepare))
            throw new FundingInvalidException("funding document not valid");

        adminMapper.updateFundingStatus(fid, "PARTICIPATE");
    }

    private boolean validateDocument(FundingDetailVO funding){
        return StringUtils.hasText(funding.getFstore_name()) ||
                StringUtils.hasText(funding.getFtitle()) ||
                StringUtils.hasText(funding.getFsummary()) ||
                StringUtils.hasText(funding.getFstory()) ||
                StringUtils.hasText(funding.getCtname());
    }

}
