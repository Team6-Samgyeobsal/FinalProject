package com.samgyeobsal.mapper;

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
        String email = "user1@email.com";
        MemberVO member = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);
        log.info(member.toString());
        Assertions.assertEquals(member.getEmail(),email);
        Assertions.assertEquals(member.getName(),"왕종휘");
    }

    @Transactional
    @Test
    void insertMember(){
        String email = "test@email.com";

        MemberVO insertMember = new MemberVO();
        insertMember.setRole(Role.ROLE_USER);
        insertMember.setType(LoginType.LOGIN_FORM);
        insertMember.setName("name");
        insertMember.setEmail(email);
        insertMember.setPassword("11111111");
        memberMapper.insertMember(insertMember);

        MemberVO findMember = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);

        Assertions.assertEquals(insertMember.getName(), findMember.getName());

    }
}
