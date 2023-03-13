package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class ProductVO {

    private String fpid;
    private int fporigin_price;
    private  int fpprice;
    private String fptitle;
    private String fpcontent;

    private List<ProdOptionVO> options;
}
