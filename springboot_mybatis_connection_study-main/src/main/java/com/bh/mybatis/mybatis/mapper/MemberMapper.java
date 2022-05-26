package com.bh.mybatis.mybatis.mapper;

import com.bh.mybatis.mybatis.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM MEMBER")
    List<Member> findAll();

    @Select("SELECT * FROM MEMBER WHERE id=#{id}")
    Member findById(@Param("id") String id);

    @Insert("SELECT * FROM MEMBER(member_id, member_password, member_name) VALUES(#{member.id}, #{member.password}, " +
            "#{member.name})")
    int insert(@Param("member") Member member);
}
