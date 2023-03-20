package com.samgyeobsal.domain.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class InsertReviewDTO {
    private String memail;
    private String fid;
    private String rcontent;
    private String rtype;
    private String rimg_url;
    private Integer rscore;

}
