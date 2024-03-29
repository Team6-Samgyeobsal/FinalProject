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
    private String fthumb;
    private int expire;
    private String fstatus;

    // 추가
    private String ctid;
    private String ctname;
    private String tid;
    private String tname;
    private String memail;
}
