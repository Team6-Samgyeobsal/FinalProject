package com.samgyeobsal.controller;

import com.samgyeobsal.domain.common.CategoryVO;
import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.ReviewMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.*;
import com.samgyeobsal.type.FundingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/web/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final ReviewService reviewService;
    private final OrderService orderService;
    private final MakerService makerService;
    private final CommonService commonService;
    private final CouponService couponService;

    @GetMapping
    public String mypage(@AuthenticationPrincipal Account account,Model model){
        model.addAttribute("couponCount",couponService.couponCount(account.getMember().getMemail()));
        return "mypage/mypage";
    }

    @GetMapping("/order")
    public String myorderPage(){
        return "mypage/myorder";
    }

    @GetMapping("/order/{orderId}")
    public String myorderDetailPage(
            @RequestParam("memail") String memail,
            @PathVariable("orderId") String orderId, Model model){
//        String email = account.getMember().getMemail();

        OrderVO order = orderService.getOrderByOrderId(orderId);

        model.addAttribute("isAlreadyExistReview", reviewService.isAlreadyExistReview("STORE", memail,order.getFid()));
        model.addAttribute("order", order);

        return "mypage/myorder_detail";
    }

    @GetMapping("/maker")
    public String mypageMaker(){
        return "mypage/mypage_maker";
    }

    @GetMapping("/maker/funding/{fundingId}")
    public String fundingPage(@PathVariable("fundingId") String fundingId, Model model) {
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        log.info("fundingMaker = {}", fundingMaker);

        model.addAttribute("fundingMaker", fundingMaker);
        return "mypage/funding_maker";
    }

    @GetMapping("/maker/funding/{fundingId}/baseInfo")
    public String fundingBaseInfo(
            @PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        FundingBaseInfoDTO baseInfo = new FundingBaseInfoDTO(fundingMaker);

        log.info("fundingMaker = {}", fundingMaker);
        List<CompetitionHyundaiVO> activeCompetitionList = commonService.getActiveCompetitionList();
        List<CategoryVO> categoryList = commonService.getCategoryList();
        if(fundingMaker.getCid() !=null && fundingMaker.getTid() != null)
            baseInfo.setCompetitionHyundai(
                    commonService.getCompetitionByCidAndTid(fundingMaker.getCid(), fundingMaker.getTid()));

        log.info("baseInfo = {}", baseInfo);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("baseInfo", baseInfo);
        model.addAttribute("activeCompetitionList", activeCompetitionList);

        return "mypage/funding_baseinfo";
    }

    @PostMapping("/maker/funding/{fundingId}/baseInfo")
    public String saveBaseInfo(@PathVariable("fundingId") String fundingId,
                               @Validated @ModelAttribute("baseInfo") FundingBaseInfoDTO baseInfoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        redirectAttributes.addAttribute("fundingId", fundingId);
        log.info("saveBaseInfo = {}", baseInfoDTO);

        // 에러 발생 시
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return "redirect:/web/mypage/maker/funding/{fundingId}/baseInfo?error=" + URLEncoder.encode(msg, "UTF-8");
        }

//        makerService.updateFundingBaseInfo(baseInfoDTO);
        redirectAttributes.addAttribute("fundingId", fundingId);

        return "redirect:/web/mypage/maker/funding/{fundingId}";
    }

    @GetMapping("/maker/funding/{fundingId}/story")
    public String fundingStory(@PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        log.info("fundingMaker = {}", fundingMaker);
        FundingStoryDTO fundingStory = new FundingStoryDTO(fundingMaker);
        fundingStory.setImgs(makerService.getFundingImgsByFundingId(fundingId));

        model.addAttribute("fundingStory", fundingStory);
        return "mypage/funding_story";
    }

    @PostMapping("/maker/funding/{fundingId}/story")
    public String fundingStoryPost(@PathVariable("fundingId") String fundingId,
                                   @Validated @ModelAttribute("fundingStory") FundingStoryDTO fundingStoryDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {

        redirectAttributes.addAttribute("fundingId", fundingId);
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return "redirect:/web/mypage/maker/funding/{fundingId}/story?error=" + URLEncoder.encode(msg, "UTF-8");
        }

        log.info("fundingStory post = {}", fundingStoryDTO);
        makerService.updateFundingStory(fundingStoryDTO);

        return "redirect:/web/mypage/maker/funding/{fundingId}";
    }

    @GetMapping("/maker/funding/{fundingId}/reward")
    public String fundingReward(@PathVariable("fundingId") String fundingId, Model model){
        FundingMakerVO fundingMaker = makerService.getFundingMakerByFundingId(fundingId);
        model.addAttribute("fundingMaker", fundingMaker);
        return "mypage/funding_reward";
    }

    @GetMapping("/coupon")
    public String coupon(){
        return "mypage/mypage_coupon";
    }
}
