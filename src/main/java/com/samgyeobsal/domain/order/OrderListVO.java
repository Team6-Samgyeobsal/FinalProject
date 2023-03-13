package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderListVO {
    private String fid;
    private int fpprice;
    private String fptitle;
    private String fpcontent;
    private String poid;
    private String pooption;
    private int amount;
}
