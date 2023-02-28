package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FundingVO {
    private String fstore_name;
    private String ftitle;
    private int totalprice;
    private String fimg1;
}
