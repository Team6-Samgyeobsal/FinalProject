package com.samgyeobsal.controller;

import com.samgyeobsal.domain.funding.ProductVO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    OrderService orderService;
    @GetMapping("/uuid")
    public Map<?, ?> getUuid(@AuthenticationPrincipal Account account, int fpprice, String fptitle) {
        // 반환할 데이터를 담을 HashMap 객체 생성
        HashMap<Object, Object> result = new HashMap<>();
        // ProductVO, MemberVO 타입의 객체를 담을 리스트 생성
        List<ProductVO> products = new ArrayList<>();
        List<MemberVO> members = new ArrayList<>();
        // ProductVO, MemberVO 타입의 객체 생성
        ProductVO prod = new ProductVO();
        MemberVO mbs = new MemberVO();


        prod.setFptitle(fptitle);
        prod.setFpcontent("난 뭐지");
        prod.setFpprice(fpprice);
        prod.setFporigin_price(fpprice);
        products.add(prod);

        mbs.setMemail(account.getMember().getMemail());
        mbs.setMpassword(account.getPassword());
        mbs.setMname(account.getName());
        mbs.setMphone(account.getMember().getMphone());
        mbs.setMloginType(account.getMember().getMloginType());
        mbs.setMrole(account.getMember().getMrole());
        mbs.setMmileage(account.getMember().getMmileage());

        members.add(mbs);



        String uuid = UUID.randomUUID().toString();
        result.put("uuid", uuid);

        result.put("products", products);
        result.put("members", members);

        return result;
    }
}
