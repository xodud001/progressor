package com.weather.progressor.member.controller;


import com.weather.progressor.member.domain.Member;
import com.weather.progressor.member.dto.FormSignInRequest;
import com.weather.progressor.member.dto.FormSignUpRequest;
import com.weather.progressor.member.service.MemberService;
import com.weather.progressor.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    @GetMapping("/signIn")
    public String signInForm(Model model) {
        model.addAttribute("member", new FormSignInRequest());
        return "member/signInForm";
    }

    @PostMapping("/signIn")
    public String signIn(@ModelAttribute("member") FormSignInRequest form, HttpServletRequest request) {
        HttpSession session = request.getSession();

        boolean isAuthorized = memberService.signIn(Member.of(form));

        if(isAuthorized){
            sessionManager.createSession(session.getId(), form.getUsername());
            return "redirect:/";
        }else{
            return "member/signUpForm";
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

}
