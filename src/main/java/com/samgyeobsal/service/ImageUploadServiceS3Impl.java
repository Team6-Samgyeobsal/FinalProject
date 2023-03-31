package com.samgyeobsal.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.samgyeobsal.domain.common.UploadImgDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Service
@Primary
@Slf4j
@RequiredArgsConstructor
public class ImageUploadServiceS3Impl implements ImageUploadService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    @Override
    public UploadImgDTO uploadImg(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();

        String savePath = bucket + "/" + folderPath;
        String saveName = uuid + "_" + originalName;

        log.info(savePath);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        try {
            amazonS3Client.putObject(savePath, saveName, file.getInputStream(), metadata);
            String url = amazonS3Client.getUrl(savePath, saveName).toString();
            return new UploadImgDTO(url, uuid, null, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return str;
    }
}
