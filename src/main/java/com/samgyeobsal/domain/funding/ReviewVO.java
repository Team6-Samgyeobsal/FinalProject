package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class ReviewVO {
    private int rscore;
    private String rtype;
    private String rimg_url;
    private String memail;
    private String rdate;
    private String fid;
    private String rcontent;
    private String recontent;
    private Date redate;
}
