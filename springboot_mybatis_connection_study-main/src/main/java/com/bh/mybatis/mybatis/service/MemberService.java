package com.bh.mybatis.mybatis.service;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMember() {
        return memberRepository.findAll();
    }

    public List<Member> findMember(String id) {
        return memberRepository.findById(id);
    }

    public void joinMember(Member member) { memberRepository.join(member); }
}
