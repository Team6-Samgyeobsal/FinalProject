package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import com.samgyeobsal.mapper.FundingMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class FundingServiceImpl implements FundingService{

    @Autowired
    private FundingMapper fundingMapper;

    @Deprecated
    @Override
    public List<FundingVO> getFundingList(FundingCriteria criteria){
        return fundingMapper.getFundingList(criteria);
    }

    /**
     * 펀딩 상세 정보 리턴
     * @param fid : 펀딩 아이디
     * @param fstatus : 펀딩 상태
     */
    @Override
    public FundingDetailVO getFundingDetail(String fid,String fstatus) {
        return fundingMapper.getFundingDetail(fid,fstatus);
    }

    /**
     * 리뷰 리스트 리턴
     * @param criteria : 리뷰 필터링 객체 - 펀딩 아이디, 정렬 기준, 리뷰 타입, 페이지 수
     */
    @Override
    public List<ReviewVO> getReviewList(ReviewCriteria criteria) {
        return fundingMapper.getReviewList(criteria);
    }

    /**
     * 해당 펀딩에서 판매중인 상품 리스트 리턴
     * @param fundingId : 펀딩 아이디
     */
    @Override
    public List<ProductVO> getProductListByFundingId(String fundingId) {
        return fundingMapper.findProductListByFundingId(fundingId);
    }

    /**
     * 해당 펀딩 상품 상세 정보 리턴
     * @param fundingId : 펀딩 아이디
     * @param productId : 상품 아이디
     */
    @Override
    public ProductVO getProductByFundingIdAndProductId(String fundingId, String productId) {
        return fundingMapper.findProductByFundingIdAndProductId(fundingId,productId);
    }

    /**
     * 리뷰 수 리턴
     * @param fid : 펀딩 아이디
     */
    @Override
    public ReviewCountVO reviewCount (String fid){
        return fundingMapper.reviewCount(fid);
    }

    /**
     * 조건에 맞는 펀딩의 총 개수
     * @param fundingCriteria : 펀딩 페이징 객체
     */
    @Override
    public int getFundingTotalCount(FundingCriteria fundingCriteria) {
        return fundingMapper.getFundingTotalCount(fundingCriteria);
    }

    /**
     * 프로시저를 호출하여 FundingCriteria에 해당하는 펀딩 리스트 출력
     * @param fundingCriteria : 펀딩 필터 객체 - 정렬 순, 음식 카테고리, 페이지, 장소
     */
    @Override
    public List<FundingVO> p_funding(FundingCriteria fundingCriteria) {

        Map<String, Object> map = new HashMap<>();
        map.put("vsort",fundingCriteria.getSort());
        map.put("vtype",fundingCriteria.getType());
        map.put("vpage",fundingCriteria.getPage());
        map.put("vtid",fundingCriteria.getTid());
        fundingMapper.p_funding(map);
        log.info(map.toString());
        List<FundingVO> list = (List<FundingVO>) map.get("POUT");
        log.info("ffcc"+list);
        return list;
    }

}
