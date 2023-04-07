package com.samgyeobsal.api;

import com.samgyeobsal.type.MyLocale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;


@Tag(name = "번역 API")
@RequestMapping("/api/translate")
@RestController
@Slf4j
public class WebSiteTranslateApi {

    @Value("${naver.client.key}")
    private String clientKey;

    @Value("${naver.client.secret.key}")
    private String clientSecretKey;

    @Value("${naver.translate.request.url}")
    private String requestUrl;

    @Value("${spring.profile.active}")
    private String profile;


    @Operation(summary = "html을 특정 언어로 번역 후 리턴")
    @Parameter(name = "html", description = "번역할 html")
    @Parameter(name = "locale", description = "번역할 언어")
    @PostMapping(consumes = "text/html")
    public ResponseEntity<String> translate(
            @RequestBody String html, @CookieValue(value = "locale", required = false) String locale){
        MyLocale myLocale = MyLocale.KO;
        if(locale != null) {
            for (MyLocale value : MyLocale.values()) {
                if (value.getAcceptLang().equals(locale)) {
                    myLocale = value;
                }
            }
        }

        log.info("myLocale = {}", myLocale);

        try{
            // prod 일 경우에만 api 호출
            if(profile.equals("prod") &&  myLocale != MyLocale.KO){
                String url = requestUrl;
                String clientId = clientKey;
                String clientSecret = clientSecretKey;
                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
                headers.set("X-NCP-APIGW-API-KEY", clientSecret);

                MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
                body.add("source", "ko");
                body.add("target", myLocale.getParam());
                body.add("html", html);

                String result = restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(html, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(html, HttpStatus.OK);
        }

    }
}
