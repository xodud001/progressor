package com.weather.progressor.app.member.controller;


import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.member.domain.SessionConst;
import com.weather.progressor.app.member.dto.FormSignInRequest;
import com.weather.progressor.app.member.dto.FormSignUpRequest;
import com.weather.progressor.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletResponse response) {
        return "redirect:/progress/calendar";
    }

    @GetMapping("/signIn")
    public String signInForm(Model model) {
        model.addAttribute("member", new FormSignInRequest());
        return "member/signInForm";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("member") FormSignInRequest form, HttpServletRequest request) {

        String redirectURL = request.getParameter("redirectURL");
        String path = StringUtils.hasText(redirectURL) || !redirectURL.equals("/") ? redirectURL : "/progress/calendar";


        HttpSession session = request.getSession();

        Member loginMember = memberService.signIn(Member.of(form));

        if(loginMember != null){
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
            return "redirect:" + path;
        }else{
            return "member/signInForm";
        }
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("member", new FormSignUpRequest());
        return "member/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUpForm(@ModelAttribute("member") FormSignUpRequest form) {

        Long savedMemberId = memberService.signUp(Member.of(form));

        if(savedMemberId > 0){
            return "redirect:/signIn";
        }else {
            return "member/signUpForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/signIn";
    }

}
