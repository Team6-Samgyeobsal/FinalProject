package com.samgyeobsal.api;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.domain.common.UploadImgDTO;
import com.samgyeobsal.service.CommonService;
import com.samgyeobsal.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/api/common")
@RequiredArgsConstructor
@RestController
public class CommonApi {

    @Value("${upload.path}")
    private String uploadPath;


    private final ImageUploadService imageUploadService;


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

}
