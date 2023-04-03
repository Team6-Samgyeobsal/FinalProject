package com.samgyeobsal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.common.CategoryVO;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.order.OrderItemVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.CommonMapper;
import com.samgyeobsal.mapper.QrCodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final CommonMapper commonMapper;

    private final RefreshTokenService refreshTokenService;


    private final QrCodeMapper qrCodeMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${review.sentimental.url}")
    private String reqUrl;

    @Value("${kakao.api.url.friends}")
    private String friendsApiUrl;

    @Value("${kakao.api.url.message}")
    private String messageApiUrl;

    @Override
    public List<CompetitionHyundaiVO> getActiveCompetitionList() {
        return commonMapper.getActiveCompetitionList();
    }

    @Override
    public CompetitionHyundaiVO getCompetitionByCidAndTid(String cid, String tid) {
        return commonMapper.getCompetitionByCidAndTid(cid, tid);
    }

    @Override
    public List<CategoryVO> getCategoryList() {
        return commonMapper.getCategoryList();
    }

    @Override
    public int getReviewScore(String review) {
        return sendReviewScoreRequest(review);
    }

    private int sendReviewScoreRequest(String review){
        int score = 3;
        try{
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(reqUrl+"?review="+review, String.class);
            return Integer.parseInt(response);
        }catch (Exception e){
            log.info("error ",e);
            return score;
        }
    }

    @Override
    public void sendOrderInfoByKakaoMessageApi(String memail, OrderVO orderVO, FundingDetailVO store) {
        OAuth2TokenVO oAuth2Token = refreshTokenService.getOAuth2TokenByEmail(memail);
        Map<String, Object> map = sendKakaoFriendsApi(oAuth2Token.getOauth2_token());
        List<String> uuids = new ArrayList<>();
        List<Map<String,Object>> objs = (List<Map<String,Object>>) map.get("elements");
        for (Map<String, Object> obj : objs) {
            String uid = (String) obj.get("uuid");
            uuids.add("\"" + uid + "\"");
        }
        sendKakaoMessageApi(uuids.stream().collect(Collectors.joining(",")),
                oAuth2Token.getOauth2_token(), orderVO, store);
    }



    private Map<String,Object> sendKakaoFriendsApi(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> res = restTemplate.exchange(
                friendsApiUrl, HttpMethod.GET, entity, String.class);
        try {
            Map<String,Object> map = objectMapper.readValue(res.getBody(), Map.class);
            log.info("res = {}", map);
            return map;
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException occur", e);
            return null;
        }
    }

    private Map<String, Object> sendKakaoMessageApi(String friendsUuids, String accessToken, OrderVO orderVO, FundingDetailVO store){
        
        //TODO : 이미지 동적으로 변경
        
        RestTemplate restTemplate = new RestTemplate();


        String qrCodeUrl = qrCodeMapper.getQrCodeString(orderVO.getOid());

        String orderTitle = orderVO.getOrders().get(0).getFptitle();
        if(orderVO.getOrders().size() > 1) orderTitle += " 외 " + (orderVO.getOrders().size() - 1) + "건";

        List<String> orderItems = new ArrayList<>();
        for (OrderItemVO order : orderVO.getOrders()) {
            String tmp = "{\n";
            tmp += "\"item\" :\""+order.getFptitle()+" "+order.getPooption()+" ("+order.getAmount()+"개)\",\n";
            tmp += "\"item_op\" : \""+(order.getFpprice()*order.getAmount())+"원\"\n";
            tmp += "}";
            orderItems.add(tmp);
        }
        int discount = Integer.parseInt(orderVO.getOorigin_price()) - orderVO.getOprice();
        orderItems.add("{\"item\" : \"할인\", \"item_op\" : \""+discount+"원\"}");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("receiver_uuids", "[" + friendsUuids + "]");
        params.add("template_object",
                "{" +
                        "  \"object_type\": \"feed\"," +
                        "  \"content\": {" +
                        "      \"title\": \""+orderVO.getFtitle()+"\"," +
                        "      \"description\": \""+orderVO.getCtname()+"\"," +
                        "      \"image_url\": \""+qrCodeUrl+"\"," +
                        "      \"image_width\": 640," +
                        "      \"image_height\": 640," +
                        "      \"link\": {" +
                        "          \"web_url\": \"http://192.168.137.75/web/mypage/order/"+orderVO.getOid()+"\"" +
                        "      }" +
                        "  }," +
                        "  \"item_content\" : {" +
                        "      \"profile_text\" :\""+orderVO.getFstore_name()+"\"," +
                        "      \"profile_image_url\" :\""+store.getFthumb()+"\"," +
                        "" +
                        "      \"title_image_text\" :\""+orderTitle+"\"," +
                        "      \"title_image_category\" : \""+orderVO.getOrders().get(0).getFpcontent()+"\"," +
                        "      \"items\" : [" +
                                orderItems.stream().collect(Collectors.joining(",")) +
                        "      ],\n" +
                        "      \"sum\" :\"Total\",\n" +
                        "      \"sum_op\" : \""+orderVO.getOprice()+"원\"\n" +
                        "  },\n" +
                        "  \"buttons\": [\n" +
                        "      {\n" +
                        "          \"title\": \"QR 확인\",\n" +
                        "          \"link\": {\n" +
                        "              \"web_url\": \"http://192.168.137.75/web/mypage/order/"+orderVO.getOid()+"\"\n" +
                        "          }\n" +
                        "      }\n" +
                        "  ]\n" +
                        "}");


        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params, headers);

        log.info("body = {}", params.get("template_object"));

        ResponseEntity<String> res = restTemplate.postForEntity(
                messageApiUrl, entity, String.class);
        try {
            Map<String,Object> map = objectMapper.readValue(res.getBody(), Map.class);
            log.info("res = {}", map);
            return map;
        } catch (JsonProcessingException e) {
            log.info("JsonProcessingException occur", e);
            return null;
        }
    }

}
