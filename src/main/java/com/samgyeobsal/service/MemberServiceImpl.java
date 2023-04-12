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

    /**
     * 회원 정보 삽입
     * @param member : 회원 정보 객체
     */
    @Override
    public void insertMember(InsertFormMemberDTO member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        memberMapper.insertMember(member.toMember());
    }

    /**
     * 회원 이메일 존재 유무 리턴
     * @param email : 회원 이메일
     */
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

    /**
     * 이메일 방식 로그인 수행
     * @param loginDTO : 이메일, 패스워드
     */
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

    /**
     * 해당 이메일의 펀딩 리스트 반환
     * @param email
     */
    @Override
    public List<FundingVO> findFindingListByEmail(String email) {
        List<FundingVO> fundingList = memberMapper.findFundingListByEmail(email);
        return fundingList;
    }

    /**
     * 해당 이메일의 입점 중인 매장 정보(스토어) 반환
     * @param email
     */
    @Override
    public FundingVO findActiveStoreByEmail(String email) {
        return memberMapper.findActiveStoreByEmail(email);
    }

    /**
     * 해당 이메일의 이미지 수정
     * @param memail : 회원 이메일
     * @param imageUrl : 이미지 url
     */
    @Override
    public void updateMemberProfile(String memail, String imageUrl) {
        memberMapper.updateMemberProfile(memail, imageUrl);
    }
}
