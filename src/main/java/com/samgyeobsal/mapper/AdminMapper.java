package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<FundingDocumentDTO> getDocumentList();

    int updateDocumentStatus(UpdateDocumentDTO updateDocument);
}
