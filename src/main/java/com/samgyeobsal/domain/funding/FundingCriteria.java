package com.samgyeobsal.domain.funding;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Schema(description = "펀딩 필터 객체")
public class FundingCriteria {

    @Parameter(name = "음식 카테고리 아이디")
    // 카테고리 (일식, 중식)
    private String type;

    // 장소 (더현대 서울, 더현대 광주)
    @Parameter(name = "더 현대 장소 아이디")
    private String place;

    // 정렬 (최신순, 인기순 .. )
    @Parameter(name = "정렬 순")
    private String sort;

    // 페이징
    @Parameter(name = "페이지 수")
    private int page;


    private String getTid(){
        if(this.place == null) return null;
        switch (this.place) {
            case "더현대 서울" :
                return "1";
            case "더현대 대구" :
                return "2";
            case "더현대 광주" :
                return "3";
            default:
                return null;
        }
    }
}
