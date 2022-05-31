package com.bh.mybatis.mybatis.mapper;

import com.bh.mybatis.mybatis.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MemberMapper {

    void joinMember (Member member);

    Member findMemberId (String id);

}
