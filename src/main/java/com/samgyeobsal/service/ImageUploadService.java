package com.samgyeobsal.service;

import com.samgyeobsal.domain.common.UploadImgDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    UploadImgDTO uploadImg(MultipartFile file);
}
