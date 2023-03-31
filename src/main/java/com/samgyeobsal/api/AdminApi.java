package com.samgyeobsal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.AdminService;
import com.samgyeobsal.service.FundingService;
import com.samgyeobsal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminApi {

    private final AdminService adminService;
    private final FundingService fundingService;

    private final RefreshTokenService refreshTokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${kakao.api.url.friends}")
    private String friendsApiUrl;

    @Value("${kakao.api.url.message}")
    private String messageApiUrl;

    @PostMapping("/document")
    public ResponseEntity<String> updateDocumentStatus(
            @RequestBody UpdateDocumentDTO document){
        log.info("document = {}", document);

        adminService.updateDocumentStatus(document);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/funding")
    public ResponseEntity<?> getAllFundingListByPlace(
            @ModelAttribute FundingCriteria fundingCriteria){
        log.info("fundingCriteria = {}", fundingCriteria);

        List<FundingVO> fundingList = fundingService.getFundingList(fundingCriteria);
        int fundingCount = fundingService.getFundingTotalCount(fundingCriteria);
        Map<String, Object> map = new HashMap<>();

        map.put("fundingList", fundingList);
        map.put("fundingCount", fundingCount);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/funding/{fid}/promote")
    public ResponseEntity<String> promoteFundingToStore(@PathVariable("fid") String fid){
        adminService.promoteFundingToStore(fid);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<?> getAllReviewList(
            @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3")int size){
        List<ReviewVO> reviewList = adminService.getAllReviewList(page,size);
        int reviewCount = adminService.getReviewCount();

        Map<String, Object> map = new HashMap<>();
        map.put("reviewList", reviewList);
        map.put("reviewCount", reviewCount);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/review/delete")
    public ResponseEntity<String> deleteReview(
            @RequestBody DeleteReviewDTO reviewDTO){
        log.info("reviewDto = {}", reviewDTO);

        adminService.deleteReview(reviewDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/totalSale")
    public ResponseEntity<List<TotalSaleDTO>> getHyundaiTotalSale() {
        List<TotalSaleDTO> hyundaiTotalSale = adminService.getHyundaiTotalSale();
        for (TotalSaleDTO saleDTO : hyundaiTotalSale) {
            log.info("saleDTO = {}", saleDTO);
        }
        return new ResponseEntity<>(hyundaiTotalSale, HttpStatus.OK);
    }

    @GetMapping("/dailySale")
    public ResponseEntity<List<DailySaleDTO>> getRecentDailySaleListByHyundai(@RequestParam(required = false) String tid){
        List<DailySaleDTO> dailySaleList = adminService.getRecentDailySaleListByHyundai(tid);
        return new ResponseEntity<>(dailySaleList, HttpStatus.OK);
    }

    @GetMapping("/categorySale")
    public ResponseEntity<List<CategorySale>> getRecentCategorySaleListByHyundai(@RequestParam(required = false) String tid) {
        List<CategorySale> categorySaleList = adminService.getRecentCategorySaleListByHyundai(tid);
        return new ResponseEntity<>(categorySaleList, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(
            @AuthenticationPrincipal Account account
    ) {
        OAuth2TokenVO oAuth2Token = refreshTokenService.getOAuth2TokenByEmail(account.getMember().getMemail());
        Map<String, Object> map = sendKakaoFriendsApi(oAuth2Token.getOauth2_token());
        List<String> uuids = new ArrayList<>();
        List<Map<String,Object>> objs = (List<Map<String,Object>>) map.get("elements");
        for (Map<String, Object> obj : objs) {
            String uid = (String) obj.get("uuid");
            uuids.add("\"" + uid + "\"");
        }

        sendKakaoMessageApi(uuids.stream().collect(Collectors.joining(",")), oAuth2Token.getOauth2_token());

        log.info("res = {}", map);
        return new ResponseEntity<>("success", HttpStatus.OK);
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

    private Map<String, Object> sendKakaoMessageApi(String friendsUuids, String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("receiver_uuids", "[" + friendsUuids + "]");
        params.add("template_object",
                "{" +
                        "  \"object_type\": \"feed\"," +
                        "  \"content\": {" +
                        "      \"title\": \"펀딩 이름\"," +
                        "      \"description\": \"펀딩 카테고리 (일식)\"," +
                        "      \"image_url\": \"https://mud-kage.kakao.com/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg\"," +
                        "      \"image_width\": 640," +
                        "      \"image_height\": 640," +
                        "      \"link\": {" +
                        "          \"web_url\": \"http://localhost/web/funding\"" +
                        "      }" +
                        "  }," +
                        "  \"item_content\" : {" +
                        "      \"profile_text\" :\"가게 이름\"," +
                        "      \"profile_image_url\" :\"https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png\"," +
                        "" +
                        "      \"title_image_text\" :\"치즈 케잌 외 3건\"," +
                        "      \"title_image_category\" : \"치즈 케잌 summary\"," +
                        "      \"items\" : [" +
                        "          {" +
                        "              \"item\" :\"Cake1 메운맛 (2개)\"," +
                        "              \"item_op\" : \"6000원\"" +
                        "          }," +
                        "          {" +
                        "              \"item\" :\"Cake1 안매운맛 (3개)\"," +
                        "              \"item_op\" : \"9000원\"" +
                        "          }," +
                        "          {" +
                        "              \"item\" :\"Cake2 ~맛\"," +
                        "              \"item_op\" : \"3000원\"" +
                        "          }," +
                        "          {" +
                        "              \"item\" :\"Cake4 ~맛\"," +
                        "              \"item_op\" : \"4000원\"" +
                        "          }," +
                        "          {" +
                        "              \"item\" :\"Cake5 ~~맛\",\n" +
                        "              \"item_op\" : \"5000원\"\n" +
                        "          }\n" +
                        "      ],\n" +
                        "      \"sum\" :\"Total\",\n" +
                        "      \"sum_op\" : \"15000원\"\n" +
                        "  },\n" +
                        "  \"buttons\": [\n" +
                        "      {\n" +
                        "          \"title\": \"웹으로 이동\",\n" +
                        "          \"link\": {\n" +
                        "              \"web_url\": \"http://localhost/web/funding\"\n" +
                        "          }\n" +
                        "      }\n" +
                        "  ]\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "\n");


        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params, headers);

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
