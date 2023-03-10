package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductDetailVO {

    private String fptitle;
    private String fporigin_price;
    private String fpprice;
    private  String fpcontent;

    List<ProductOptionVO> options;
}
