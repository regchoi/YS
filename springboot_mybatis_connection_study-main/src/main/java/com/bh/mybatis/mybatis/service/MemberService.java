package com.bh.mybatis.mybatis.service;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper memberMapper;

    public Member findMemberId(String id) {
        return memberMapper.findMemberId(id);
    }

    public void joinMember(Member member) { memberMapper.joinMember(member); }
}
