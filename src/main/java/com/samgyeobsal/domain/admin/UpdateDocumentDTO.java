package com.samgyeobsal.domain.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "펀딩서류 상태변경")
public class UpdateDocumentDTO {

    @Schema(title = "펀딩 아이디", required = true)
    private String fid;
    @Schema(title = "펀딩서류 합격 여부", required = true)
    private Boolean isPass;

    public String getStatus() {
        return isPass ? "FUNDING" : "FAIL";
    }
}
