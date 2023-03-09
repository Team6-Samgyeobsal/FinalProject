package com.samgyeobsal.domain.maker;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FundingBaseInfoDTO {
    private String fid;
    private String fstore_name;
    private String fthumb;
    private String ctid;
    private String ctname;


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
