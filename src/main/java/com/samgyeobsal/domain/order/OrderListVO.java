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
    private int fporigin_price;
    private String fptitle;
    private String fpcontent;
    private List<OrderListDetailVO> orderListDetail;


    public int getTotalAmount(){
        int sumAmount = 0;
        for (OrderListDetailVO orderListDetailVO : orderListDetail) {
            sumAmount += orderListDetailVO.getAmount();
        }
        return sumAmount;
    }

    public int getSumprice(){
        return getTotalAmount() * this.fpprice;
    }
}

