package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.type.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void insertMember(InsertFormMemberDTO member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        memberMapper.insertMember(member.toMember());
    }

    @Override
    public boolean isExist(String email) {
        MemberVO memberVO = null;
        for(LoginType type : LoginType.values()){
            MemberVO tmp = memberMapper.findMemberByEmail(email, type.toString());
            if(tmp != null){
                memberVO = tmp;
                break;
            }
        }
        return memberVO != null;
    }

    @Override
    public MemberVO login(LoginDTO loginDTO) {
        Account account = (Account) userDetailsService.loadUserByUsername(loginDTO.getEmail());
        if(account == null){
            throw new RuntimeException("회원 존재 X");
        }
        log.info("account = {}", account);
        MemberVO memberVO = account.getMember();
        log.info("member = {}", memberVO);
        if (!passwordEncoder.matches(loginDTO.getPassword(), memberVO.getMpassword())) {
            throw new RuntimeException("비밀번호 다름");
        }
        return memberVO;
    }

    @Override
    public List<FundingVO> findFindingListByEmail(String email) {
        List<FundingVO> fundingList = memberMapper.findFundingListByEmail(email);
        return fundingList;
    }
}
