package com.samgyeobsal.domain.review;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "리뷰 답글 객체")
public class ReplyReviewVO {

    @Parameter(description = "답글 내용")
    private String recontent;
    @Parameter(description = "펀딩아이디")
    private String fid;
    @Parameter(description = "답글 작성자")
    private String memail;
    @Parameter(description = "답글 타입")
    private String rtype;


}
