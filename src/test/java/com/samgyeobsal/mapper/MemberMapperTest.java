package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void findMemberByEmail(){
        String email = "user@gmail.com";
        MemberVO member = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);
        log.info(member.toString());
        Assertions.assertEquals(member.getMemail(),email);
        Assertions.assertEquals(member.getMname(),"user1");
    }

    @Test
    void findMemberByEmail2(){
        String email = "wangjh789@gmail.com";
        MemberVO memberVO = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);
        Assertions.assertNull(memberVO);
    }
    @Test
    void findMemberByEmail3(){
        String email = "wangjh789@gmail.com";
        MemberVO memberVO = memberMapper.findMemberByEmail(email, LoginType.LOGIN_GOOGLE);
        Assertions.assertEquals(email, memberVO.getMemail());
    }

    @Test
    void findMemberByEmail4(){
        String email = "wangjh789@gmail.com";
        MemberVO memberVO = memberMapper.findMemberByEmail(email, null);
        Assertions.assertEquals(email, memberVO.getMemail());
    }

    @Transactional
    @Test
    void insertMember(){
        String email = "test@email.com";

        InsertFormMemberDTO insertMember = new InsertFormMemberDTO();
        insertMember.setEmail(email);
        insertMember.setName("name");
        insertMember.setPassword("1111111");
        insertMember.setPhone("1011111111");

        MemberVO member = insertMember.toMember();
        memberMapper.insertMember(member);

        MemberVO findMember = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);

        Assertions.assertEquals(member.getMname(), findMember.getMname());

    }

}
