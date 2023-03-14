package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderListVO {
    private String fpid;
    private int fpprice;
    private String fptitle;
    private String fpcontent;
    private int sumprice;
    private int sumamount;
    private List<OrderListDetailVO> orderListDetail;
}
