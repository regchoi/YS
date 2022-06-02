package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private MemberService memberService;
    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("login")
    public String loginG(Model model, LoginForm loginForm) {
        model.addAttribute("loginForm", loginForm);
        return "/board/login";
    }
    @PostMapping("login")
    public String loginP(LoginForm loginForm, HttpServletRequest request) {
        return memberService.loginMember(loginForm, request);
    }


}
