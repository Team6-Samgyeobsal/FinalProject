package com.samgyeobsal.service;

import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import com.samgyeobsal.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public List<FundingDocumentDTO> getDocumentList() {
        return adminMapper.getDocumentList();
    }

    @Override
    public void updateDocumentStatus(UpdateDocumentDTO updateDocument) {
        int row = adminMapper.updateDocumentStatus(updateDocument);
        if(row == 0) throw new RuntimeException("update Error occur");
    }
}
