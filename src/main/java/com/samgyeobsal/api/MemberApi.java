package com.samgyeobsal.api;

import com.samgyeobsal.domain.common.UploadImgDTO;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.ImageUploadService;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.OrderService;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberService memberService;
    private final OrderService orderService;

    private final ImageUploadService imageUploadService;

    @GetMapping("/funding")
    public ResponseEntity<?> findMember(@AuthenticationPrincipal Account account){
        List<FundingVO> findingListByEmail = memberService.findFindingListByEmail(account.getMember().getMemail());

        return new ResponseEntity<>(findingListByEmail, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderVO>> getMyOrders(
            @AuthenticationPrincipal Account account){
        String email = account.getMember().getMemail();
        List<OrderVO> orderList = orderService.getMyOrderList(email);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping("/uploadProfile")
    public ResponseEntity<UploadImgDTO> uploadProfile(MultipartFile uploadFile, String memail){

        if (!uploadFile.getContentType().startsWith("image")) {
            log.warn("not image file");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        UploadImgDTO uploadImgDTO = imageUploadService.uploadImg(uploadFile);


        memberService.updateMemberProfile(memail, uploadImgDTO.getImageURL());

        return new ResponseEntity<>(uploadImgDTO, HttpStatus.OK);

    }
}
