package com.samgyeobsal.api;

import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import com.samgyeobsal.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminApi {

    private final AdminService adminService;

    @PostMapping("/document")
    public ResponseEntity<String> updateDocumentStatus(
            @RequestBody UpdateDocumentDTO document){
        log.info("document = {}", document);

        adminService.updateDocumentStatus(document);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
