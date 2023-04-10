package com.samgyeobsal.domain.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {

    private String fid;
    private String fstore_name;
    private String ftitle;
    private String fthumb;
    private String cid;
    private String fstatus;
    private String ctname;
    private String tname;
    private double fscore;
    private int min_price;
    private String faddress;

    public String getFstatusKo(){
        switch (this.fstatus) {
            case "FUNDING" :
                return "펀딩";
            case "STORE" :
                return "스토어";
            case "END" :
                return "명예의 전당";
            default:
                return null;
        }
    }

}
