package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemVO {

    private String poid;
    private int amount;
    private String pooption;
    private String fpid;
    private String fid;
    private Integer fpprice;
    private Integer fporigin_price;
    private String fptitle;
    private String fpcontent;
}
