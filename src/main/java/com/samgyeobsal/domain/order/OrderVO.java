package com.samgyeobsal.domain.order;

import com.samgyeobsal.type.FundingStatus;
import com.samgyeobsal.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderVO {
    private String oid;
    private String ophone;
    private String omemo;
    private String oused_mileage;
    private String oorigin_price;
    private Integer oprice;
    private String ostatus;
    private Date odate;
    private String memail;
    private String pmcode;
    private Date qrused_date;
    private String cpid;

    private String fstore_name;
    private String ftitle;
    private String fsummary;
    private String fstory;
    private Date fdate;
    private FundingStatus fstatus;

    private String ctid;
    private String ctname;

    List<OrderItemVO> orders;

    public String getOdateString(){
        return DateUtil.dateToStr(odate);
    }


}
