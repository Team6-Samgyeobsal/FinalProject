package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FundingVO {

    private String fid;
    private String fstore_name;
    private String ftitle;
    private long totalprice;
    private String fiurl; // 섬네일 이미지
    private int expire;
}
