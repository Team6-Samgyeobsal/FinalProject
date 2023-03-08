package com.samgyeobsal.domain.maker;

import com.samgyeobsal.type.FundingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FundingMakerVO {

    private String fid;
    private String fstore_name;
    private String ftitle;
    private String fsummary;
    private String fstory;
    private String fdate;
    private FundingStatus fstatus;
    private String memail;

    private String ctid;
    private String ctname;

    private String fthumb;

    private boolean hasProduct;
    private boolean hasImg;

}
