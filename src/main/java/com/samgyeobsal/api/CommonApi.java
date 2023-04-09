package com.samgyeobsal.api;

import com.samgyeobsal.domain.common.UploadImgDTO;
import com.samgyeobsal.service.ImageUploadService;
import com.samgyeobsal.service.KaKaoMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@RequestMapping("/api/common")
@Tag(name = "공통 API")
@RequiredArgsConstructor
@RestController
public class CommonApi {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${admin.email}")
    private String adminEmail;

    private final ImageUploadService imageUploadService;

    private final KaKaoMessageService kaKaoMessageService;


    @Operation(summary = "로컬 환경의 이미지를 리턴", deprecated = true,
            description = "로컬환경의 properties에 지정된 경로에 저장된 이미지를 리턴합니다.")
    @Parameter(name = "imgName", description = "저장된 이미지 이름")
    @GetMapping("/displayImg")
    public ResponseEntity<byte[]> displayImg(@RequestParam("imgName") String imgName) {

        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(imgName, "UTF-8");

            File file = new File(uploadPath + File.separator + srcFileName);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Operation(summary = "이미지 업로드", description = "이미지를 지정된 곳에 업로드 합니다. (S3 bucket)")
    @PostMapping("/uploadImg")
    public ResponseEntity<List<UploadImgDTO>> uploadImg(MultipartFile[] uploadFiles) {
        List<UploadImgDTO> result = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            if (!uploadFile.getContentType().startsWith("image")) {
                log.warn("not image file");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            UploadImgDTO uploadImgDTO = imageUploadService.uploadImg(uploadFile);

            result.add(uploadImgDTO);
        }
        log.info("result = {}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/message")
    @Operation(summary = "주문 정보를 담아 카카오톡 메시지 전송", description = "주문자에게 카카오 메시지를 전송합니다.")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> map){
        String oid = map.get("oid");
        String msg = map.get("msg");
        String email = adminEmail;
        kaKaoMessageService.sendWaitingInfoByKakaoMessage(email, msg, oid);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
