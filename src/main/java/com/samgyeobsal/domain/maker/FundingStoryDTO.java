package com.samgyeobsal.domain.maker;


import com.samgyeobsal.domain.funding.FundingImgVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FundingStoryDTO {

    @NotBlank(message = "잘못된 요청입니다")
    private String fid;
    @NotBlank(message = "펀딩 제목을 입력해주세요")
    private String ftitle;
    @NotBlank(message = "펀딩 요약을 입력해주세요")
    private String fsummary;
    @NotBlank(message = "펀딩 스토리를 입력해주세요")
    private String fstory;

    @NotNull(message = "대표사진을 업로드 해주세요")
    private List<FundingImgVO> imgs;

    public FundingStoryDTO(FundingMakerVO fundingMaker){
        this.fid = fundingMaker.getFid();
        this.ftitle = fundingMaker.getFtitle();
        this.fstory = fundingMaker.getFstory();
        this.fsummary = fundingMaker.getFsummary();
    }
}
