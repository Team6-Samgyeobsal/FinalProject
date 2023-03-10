package com.samgyeobsal.domain.maker;


import com.samgyeobsal.domain.funding.FundingImgVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FundingStoryDTO {

    private String fid;
    private String ftitle;
    private String fsummary;
    private String fstory;

    private List<FundingImgVO> imgs;

    public FundingStoryDTO(FundingMakerVO fundingMaker){
        this.fid = fundingMaker.getFid();
        this.ftitle = fundingMaker.getFtitle();
        this.fstory = fundingMaker.getFstory();
        this.fsummary = fundingMaker.getFsummary();
    }
}
