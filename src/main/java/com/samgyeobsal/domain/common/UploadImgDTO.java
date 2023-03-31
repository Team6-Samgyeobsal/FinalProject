package com.samgyeobsal.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadImgDTO implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

    private boolean isLocal;

    public String getImageURL(){
        try{
            if(isLocal){
                String url = folderPath + "/" + uuid + "_" + fileName;
                return "http://localhost/api/common/displayImg?imgName="+URLEncoder.encode(url,"UTF-8");
            }else{
                return fileName;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
