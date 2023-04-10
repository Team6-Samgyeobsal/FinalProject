package com.samgyeobsal.domain.admin;

import lombok.Data;

import java.util.Date;

@Data
public class FundingDocumentDTO {

    private String fid;
    private String fstore_name;
    private String ftitle;
    private Date fdate;
    private String tname;
    private String memail;
    private String ctname;

}
