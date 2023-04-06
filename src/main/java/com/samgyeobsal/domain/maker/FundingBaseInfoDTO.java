package com.samgyeobsal.domain.maker;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class FundingBaseInfoDTO {
    @NotBlank(message = "잘못된 요청입니다")
    private String fid;
    @NotBlank(message = "가게이름을 입력해주세요")
    private String fstore_name;
    @NotBlank(message = "대표 이미지를 업로드 해주세요")
    private String fthumb;
    @NotBlank(message = "카테고리를 선택해주세요")
    private String ctid;
    private String ctname;

    @NotBlank(message = "주소를 입력해주세요")
    private String faddress;

    @NotBlank(message = "장소를 선택해주세요")
    private String cid;
    private Date cfunding_start_date;
    private Date cfunding_end_date;

    private String tid;
    private String tname;


    public FundingBaseInfoDTO(FundingMakerVO vo){
        this.fid = vo.getFid();
        this.fstore_name = vo.getFstore_name();
        this.fthumb = vo.getFthumb();
        this.ctid = vo.getCtid();
        this.ctname = vo.getCtname();
        this.tid = vo.getTid();
        this.faddress = vo.getFaddress();
    }

    public void setCompetitionHyundai(CompetitionHyundaiVO competition) {
        this.cid = competition.getCid();
        this.cfunding_start_date = competition.getCfunding_start_date();
        this.cfunding_end_date = competition.getCfunding_end_date();
        this.tid = competition.getTid();
        this.tname = competition.getTname();
    }

    public String getStringFundingStart(){
        return DateUtil.dateToStr(cfunding_start_date);
    }
    public String getStringFundingEnd(){
        return DateUtil.dateToStr(cfunding_end_date);
    }


}
