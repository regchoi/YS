package com.bh.mybatis.mybatis.mapper;

import com.bh.mybatis.mybatis.domain.MainBoard;

import java.util.List;

public interface BoardMapper {
    void createMainBoard(MainBoard mainBoard);

    int boardCount();

    List<MainBoard> getList();
}
