package com.bh.mybatis.mybatis.service;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.JoinForm;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper memberMapper;

    public Member findMemberId(String id) {
        return memberMapper.findMemberId(id);
    }

    public String joinMember(Model model, LoginForm loginForm, JoinForm joinForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(!(joinForm.getPassword().equals(joinForm.getCheckPassword()))) {
            session.setAttribute("message", "비밀번호를 다시 확인해주세요");
            return "board/join";}
        try {
            memberMapper.findMemberId(joinForm.getId());
            session.setAttribute("message", "이미 존재하는 아이디입니다");
            return "board/join";
        } catch (Exception e) {
            Member member = new Member();
            member.setId(joinForm.getId());
            member.setPassword(joinForm.getPassword());
            member.setName(joinForm.getName());
            memberMapper.joinMember(member);
            model.addAttribute("loginForm", loginForm);
            session.setAttribute("message", "회원가입이 완료되었습니다");
            return "redirect:";
        }
    }

    public String loginMember(LoginForm loginForm, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member member = new Member();
        try{
            member = memberMapper.findMemberId(loginForm.getId());

            if(member.getPassword().equals(loginForm.getPassword())){
                session.setAttribute("login", "logined");
                session.setAttribute("member", member);
                session.setAttribute("message", "로그인 되었습니다.");
                System.out.println("==================================================================");
                System.out.println(memberMapper.findMemberId(loginForm.getId()).getMemberId());
                System.out.println(member);
                System.out.println("==================================================================");
                return "redirect:";
            } else{
                session.setAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다");
                return "/board/login";
            }

        } catch (Exception e) {
            session.setAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다");
            return "/board/login";
        }

    }


}
