package com.samgyeobsal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverTransService {

    /**
     * 번역할 문장, 번역할 언어, 번역 결과로 얻고 싶은 언어를 인자로 받아
     * 네이버 번역 API를 호출하여 번역 결과를 반환
     * @param s : 번역할 문장
     * @param source : 번역할 문장의 언어 코드
     * @param target : 번역 결과로 얻고자 하는 언어 코드
     */
    public String getTransSentence(String s, String source, String target) throws IOException {

        String clientId = "CLwUBRvek8oAJwcnwtly";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "P5ezxAXYey";//애플리케이션 클라이언트 시크릿값";

        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
            text = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        String responseBody = post(apiURL, requestHeaders, text, source, target);
        System.out.println("responseBody = " + responseBody);

        return convertToData(responseBody);
    }


    /**
     * 함수에서는 URL과 HTTP 요청 헤더, POST 파라미터 등을 설정하고,
     * HTTP 연결을 수행하여 응답 결과를 반환
     * @param apiUrl: API 호출 url
     * @param requestHeaders : HTTP 요청 헤더 정보를 담고 있는 Map 객체
     * @param text : 번역할 문장
     * @param source : 번역할 문장의 언어 코드
     * @param target : 번역 결과로 얻고자 하는 언어 코드
     */
    private String post(String apiUrl, Map<String, String> requestHeaders, String text, String source, String target){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=" + source + "&target=" + target + "&text=" + text;
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    /**
     * 함수는 주어진 URL로부터 HttpURLConnection 객체를 생성하여 리턴
     * @param apiUrl: API 호출 url
     */
    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    /**
     * 함수는 HTTP 응답 본문을 읽어들이는 기능을 수행
     * @param body: HTTP 응답 본문을 나타내는 InputStream 객체
     */
    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    /**
     * 함수에서는 JSON 문자열에서 번역 결과 문자열을 추출하여 리턴
     * @param responseBody : HTTP 응답 결과를 나타내는 JSON 형식의 문자열
     */
    public String convertToData(String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(responseBody);
        JsonNode translatedNode = node.path("message").path("result").path("translatedText");
        return translatedNode.asText();
    }
}
