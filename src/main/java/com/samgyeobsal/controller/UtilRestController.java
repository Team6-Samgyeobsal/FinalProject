package com.samgyeobsal.controller;

import com.samgyeobsal.domain.funding.ProductVO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @filename UtilRestController
 * @author 최태승
 * @since 2023.03.04
 * create uuid
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.04	최태승		최초 생성
 * </pre>
 */
@RestController
@RequestMapping("/web/util")
public class UtilRestController {

    /**
     * Map 타입으로 반환하는 getuuid() 메서드 정의
     * @return
     */
    @GetMapping("/uuid")
    public Map<?, ?> getUuid() {
        // 반환할 데이터를 담을 HashMap 객체 생성
        HashMap<Object, Object> result = new HashMap<>();
        // ProductVO, MemberVO 타입의 객체를 담을 리스트 생성
        List<ProductVO> products = new ArrayList<>();
        List<MemberVO> members = new ArrayList<>();
        // ProductVO, MemberVO 타입의 객체 생성
        ProductVO prod = new ProductVO();
        MemberVO mbs = new MemberVO();

        prod.setFptitle("수제 살치살 소갈비 1.2kg (6대) + 후식 냉면 세트");
        prod.setFpcontent("고급 부위인 살치살을 수작업으로 지방 및 근막을 제거하여 품질 및 식감을 개선하고 20년 경력의 육부장이 직접 칼집을 넣어 과일 양념과 고기가 잘 어우러져 고급 매장에서 먹는 듯한 프리미엄 갈비 입니다.");
        prod.setFpprice(43900);
        prod.setFporigin_price(50000);
        products.add(prod);

        mbs.setMemail("user@gmail.com");
        mbs.setMpassword("1111");
        mbs.setMname("user1");
        mbs.setMphone("01023398197");
        mbs.setMloginType(LoginType.LOGIN_FORM);
        mbs.setMrole(Role.ROLE_USER);
        mbs.setMmileage(1000);

        members.add(mbs);



        String uuid = UUID.randomUUID().toString();
        result.put("uuid", uuid);

        result.put("products", products);
        result.put("members", members);

        return result;
    }
}
