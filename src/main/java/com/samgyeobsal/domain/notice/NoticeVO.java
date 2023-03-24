package com.samgyeobsal.domain.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class NoticeVO {

    private String nid;
    private String ntitle;
    private String ndate;
    private String nstatus;
    private String nthumb;
    private String ncontent;
}
