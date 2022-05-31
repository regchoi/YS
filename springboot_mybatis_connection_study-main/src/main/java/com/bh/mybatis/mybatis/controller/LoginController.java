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
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        Member member = new Member();
        try{
            member = memberService.findMemberId(loginForm.getId());
        } catch (Exception e){
            member.setId("ddd");
        }
        System.out.println("====================================");
        System.out.println(member.getId());
        System.out.println("====================================");
        if(!(member.getId().equals(loginForm.getId()))){
            session.setAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다");
            return "/board/login";
        } else if(!(member.getPassword().equals(loginForm.getPassword()))){
            session.setAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다");
            return "/board/login";
        } else {
            session.setAttribute("login", "logined");
            session.setAttribute("message", "로그인 되었습니다.");
            return "redirect:";
        }
    }


}
