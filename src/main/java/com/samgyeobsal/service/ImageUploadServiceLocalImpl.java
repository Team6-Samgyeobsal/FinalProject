package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.UploadImgDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Deprecated
@Service
@RequiredArgsConstructor
public class ImageUploadServiceLocalImpl implements ImageUploadService {

    @Value("${upload.path}")
    private String uploadPath;


    /**
     * 로컬 환경에 이미지 저장 후, 파일 정보 리턴
     * @param uploadFile : 업로드할 파일
     */
    @Override
    public UploadImgDTO uploadImg(MultipartFile uploadFile) {
        String originalName = uploadFile.getOriginalFilename();
        log.info("originalName = {}", originalName);
        String folderPath = makeFolder();
        log.info("folderPath = {}", folderPath);

        String uuid = UUID.randomUUID().toString();

        String saveName = uploadPath + File.separator + folderPath
                + File.separator + uuid + "_" + originalName;
        log.info(saveName);
        Path savePath = Paths.get(saveName);
        try {
            uploadFile.transferTo(savePath);
            return new UploadImgDTO(originalName, uuid, folderPath, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("local file uplaod error");
        }
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (!uploadPathFolder.exists())
            uploadPathFolder.mkdirs();
        return folderPath;
    }
}
