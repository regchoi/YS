package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.mapper.MemberMapper;
import com.bh.mybatis.mybatis.service.MemberService;
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
public class HomeController {

    private MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    private MemberMapper memberMapper;

    public HomeController() {
    }

    @GetMapping
    public String loginG(Model model, LoginForm loginForm) {
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping
    public String loginP(LoginForm loginForm, HttpServletRequest request) {
        Member member = new Member();
        HttpSession session = request.getSession();

        try {
            member = memberService.findMember(loginForm.getId());
        } catch (NullPointerException e){
            System.out.println("=========================================");

            member.setId("ddd");
            System.out.println(member.getId());

        }
        System.out.println("=========================================");

        if(member.getId().equals(loginForm.getId())){
            return "나중에 게시판";
        } else {
            System.out.println("=========================================");
            System.out.println(loginForm.getId());
            System.out.println("=========================================");
            System.out.println(member.getId());
            session.setAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다");
            return "redirect:";
        }

    }
}