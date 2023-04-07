package com.samgyeobsal.domain.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Schema(description = "리뷰 생성 객체")
public class InsertReviewDTO {
    @Schema(description = "작성자 이메일", required = true)
    private String memail;
    @Schema(description = "펀딩아이디",required = true)
    private String fid;
    @Schema(description = "댓글 내용",required = true)
    private String rcontent;
    @Schema(description = "댓글 타입",required = true)
    private String rtype;
    @Schema(description = "댓글 첨부 이미지")
    private String rimg_url;
    @Schema(description = "댓글 평점")
    private Integer rscore;

}
