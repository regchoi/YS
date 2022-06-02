package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.MainBoard;
import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.JoinForm;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.service.BoardService;
import com.bh.mybatis.mybatis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

    private final MemberService memberService;
    private final BoardService boardService;

    @Autowired
    public HomeController(MemberService memberService, BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @GetMapping("join")
    public String joinG(Model model, JoinForm joinForm) {
        model.addAttribute("joinForm", joinForm);
        return "board/join";
    }

    @PostMapping("join")
    public String joinP(Model model, LoginForm loginForm, JoinForm joinForm, HttpServletRequest request) {
        return memberService.joinMember(model, loginForm, joinForm, request);

    }
    @GetMapping
    public String loginG(Model model) {
        return "board/list";
    }

    @GetMapping("create")
    public String boardCreate(Model model) {
        return "smarteditor/newPost";
    }

    @RequestMapping("savePost")
    public String savePost(HttpServletRequest request, MainBoard mainBoard) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        System.out.println("==================================================================");
        System.out.println(member);
        System.out.println("==================================================================");
        ModelMap model = new ModelMap();
        model.addAttribute("result", HttpStatus.OK);
        mainBoard.setMemberId(member.getMemberId());
        boardService.createBoard(mainBoard);
        return "";
    }
}