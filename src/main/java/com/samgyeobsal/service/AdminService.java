package com.samgyeobsal.service;

import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;

import java.util.List;

public interface AdminService {

    List<FundingDocumentDTO> getDocumentList();

    void updateDocumentStatus(UpdateDocumentDTO document);
}
