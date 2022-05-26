package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/rest")
//    public List<Member> getMember(){
//        return memberService.getMember();
//    }
    public Member getMember(){
        String dd = "A";
        return memberService.findMember(dd);
    }

    @GetMapping("/user/{id}")
    public Member findMember(@PathVariable("id") String id){
        return memberService.findMember(id);
    }
}
