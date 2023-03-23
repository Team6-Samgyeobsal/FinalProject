package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FundingCriteria {

    // 카테고리 (일식, 중식)
    private String type;

    // 장소 (더현대 서울, 더현대 광주)
    private String place;

    // 정렬 (최신순, 인기순 .. )
    private String sort;

    // 페이징
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
                return "";
        }
    }
}
