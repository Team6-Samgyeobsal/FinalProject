package com.samgyeobsal.api;

import com.samgyeobsal.domain.common.UploadImgDTO;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.ImageUploadService;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "회원 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberService memberService;
    private final OrderService orderService;

    private final ImageUploadService imageUploadService;

    @Operation(summary = "유저 펀딩 리턴", description = "로그인한 유저가 생성한 펀딩들을 리턴합니다.")
    @GetMapping("/funding")
    public ResponseEntity<?> findMember(@AuthenticationPrincipal Account account){
        List<FundingVO> findingListByEmail = memberService.findFindingListByEmail(account.getMember().getMemail());

        return new ResponseEntity<>(findingListByEmail, HttpStatus.OK);
    }

    @Operation(summary = "유저 주문 기록 리턴", description = "로그인한 유저의 주문 기록들을 리턴합니다.")
    @GetMapping("/order")
    public ResponseEntity<List<OrderVO>> getMyOrders(
            @AuthenticationPrincipal Account account){
        String email = account.getMember().getMemail();
        List<OrderVO> orderList = orderService.getMyOrderList(email);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "유저 프로필 사진 업로드", description = "로그인한 유저의 프로필 사진을 업로드합니다.")
    @PostMapping("/uploadProfile")
    public ResponseEntity<UploadImgDTO> uploadProfile(
            MultipartFile uploadFile, @AuthenticationPrincipal Account account) {
        String memail = account.getMember().getMemail();

        if (!uploadFile.getContentType().startsWith("image")) {
            log.warn("not image file");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        UploadImgDTO uploadImgDTO = imageUploadService.uploadImg(uploadFile);

        memberService.updateMemberProfile(memail, uploadImgDTO.getImageURL());

        return new ResponseEntity<>(uploadImgDTO, HttpStatus.OK);

    }
}
