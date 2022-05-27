package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.JoinForm;
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


    @GetMapping("join")
    public String joinG(Model model, JoinForm joinForm) {
        model.addAttribute("joinForm", joinForm);
        return "board/join";
    }

    @PostMapping("join")
    public String joinP(Model model, LoginForm loginForm, JoinForm joinForm, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(!(memberService.findMember(joinForm.getId()).isEmpty())){
            session.setAttribute("message", "이미 존재하는 아이디입니다");
            return "board/join";
        } else if(!(joinForm.getPassword().equals(joinForm.getCheckPassword()))) {
            session.setAttribute("message", "비밀번호를 다시 확인해주세요");
            return "board/join";
        } else{
            Member member = new Member();
            member.setId(joinForm.getId());
            member.setPassword(joinForm.getPassword());
            member.setName(joinForm.getName());
            memberService.joinMember(member);
            model.addAttribute("loginForm", loginForm);
            session.setAttribute("message", "회원가입이 완료되었습니다");
            return "redirect:";
        }

    }
    @GetMapping
    public String loginG(Model model) {
        return "board/list";
    }
}