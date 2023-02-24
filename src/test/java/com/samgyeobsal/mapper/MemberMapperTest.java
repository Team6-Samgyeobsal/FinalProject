package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.type.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void findMemberByEmail(){
        String email = "user1@email.com";
        MemberVO member = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);
        log.info(member.toString());
        Assertions.assertEquals(member.getEmail(),email);
        Assertions.assertEquals(member.getName(),"왕종휘");
    }
}
