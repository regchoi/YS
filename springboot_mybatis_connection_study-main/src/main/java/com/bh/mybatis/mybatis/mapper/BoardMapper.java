package com.bh.mybatis.mybatis.mapper;

import com.bh.mybatis.mybatis.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardMapper {
    int boardCount();

    List<Board> getList();
}
