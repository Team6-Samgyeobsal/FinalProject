package com.samgyeobsal.domain.admin;

import lombok.Data;

@Data
public class UpdateDocumentDTO {

    private String fid;
    private Boolean isPass;

    public String getStatus() {
        return isPass ? "FUNDING" : "FAIL";
    }
}
