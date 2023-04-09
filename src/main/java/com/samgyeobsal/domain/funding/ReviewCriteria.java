package com.samgyeobsal.domain.funding;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "리뷰 필터 객체")
public class ReviewCriteria {
    @Parameter(name = "펀딩아이디", required = true)
    private String fid;
    @Parameter(name = "정렬 기준")
    private String sort;
    @Parameter(name = "리뷰 타입 (응원 / 후기)", required = true)
    private String type;
    @Parameter(name = "리뷰 페이지 수")
    private int page;
}
