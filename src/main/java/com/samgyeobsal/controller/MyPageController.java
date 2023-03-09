package com.samgyeobsal.controller;

import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.service.CommonService;
import com.samgyeobsal.service.MakerService;
import com.samgyeobsal.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MemberService memberService;
    private final MakerService makerService;
    private final CommonService commonService;

    @GetMapping
    public String mypage(){
        return "mypage/mypage";
    }

    @GetMapping("/order")
    public String myorderPage(){
        return "mypage/myorder";
    }

    @GetMapping("/order/{orderId}")
    public String myorderDetailPage(@PathVariable("orderId") String orderId){
        return "mypage/myorder_detail";
    }

    @GetMapping("/maker")
    public String mypageMaker(){
        return "mypage/mypage_maker";
    }

    @GetMapping("/maker/funding/{fundingId}")
    public String fundingPage(@PathVariable("fundingId") String fundingId, Model model) {
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        model.addAttribute("fundingMaker", fundingMaker);
        return "mypage/funding_maker";
    }

    @GetMapping("/maker/funding/{fundingId}/baseInfo")
    public String fundingBaseInfo(@PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        FundingBaseInfoDTO baseInfo = new FundingBaseInfoDTO(fundingMaker);

        if(fundingMaker.getCid() !=null && fundingMaker.getTid() != null){
            baseInfo.setCompetitionHyundai(
                    commonService.getCompetitionByCidAndTid(fundingMaker.getCid(), fundingMaker.getTid()));
        }
        log.info("baseInfo = {}", baseInfo);

        model.addAttribute("baseInfo", baseInfo);
        return "mypage/funding_baseinfo";
    }

    @GetMapping("/maker/funding/{fundingId}/story")
    public String fundingStory(@PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        model.addAttribute("fundingMaker", fundingMaker);
        return "mypage/funding_story";
    }

    @GetMapping("/maker/funding/{fundingId}/reward")
    public String fundingReward(@PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        model.addAttribute("fundingMaker", fundingMaker);
        return "mypage/funding_reward";
    }
}
