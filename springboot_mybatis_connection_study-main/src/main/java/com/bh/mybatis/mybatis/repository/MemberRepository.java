package com.bh.mybatis.mybatis.repository;

import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    private MemberMapper memberMapper;

    @Autowired
    public MemberRepository(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public List<Member> findById(String id) {
        return memberMapper.findById(id);
    }

    public void join(Member member) { memberMapper.insert(member);}

}
