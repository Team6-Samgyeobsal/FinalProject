package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDetailVO {

    private String fpid;
    private String fptitle;
    private int fporigin_price;
    private int fpprice;
    private  String fpcontent;

    List<ProductOptionVO> options;
}
