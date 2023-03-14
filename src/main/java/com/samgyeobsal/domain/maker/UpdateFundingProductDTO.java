package com.samgyeobsal.domain.maker;


import com.samgyeobsal.domain.funding.ProdOptionVO;
import lombok.Data;
import java.util.List;

@Data
public class UpdateFundingProductDTO {
    private String fid;
    private String fpid;
    private String price;
    private String originPrice;
    private String title;
    private String content;
    private List<ProdOptionVO> opts;


}
