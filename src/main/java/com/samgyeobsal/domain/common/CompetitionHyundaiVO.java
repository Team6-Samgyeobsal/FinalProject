package com.samgyeobsal.domain.common;

import com.samgyeobsal.util.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class CompetitionHyundaiVO {
    private String cid;
    private Date cfunding_start_date;
    private Date cfunding_end_date;
    private Date cstore_start_date;
    private Date cstore_end_date;

    private String tid;
    private String tname;

    public String getStringFundingStart(){
        return DateUtil.dateToStr(cfunding_start_date);
    }
    public String getStringFundingEnd(){
        return DateUtil.dateToStr(cfunding_end_date);
    }
}
