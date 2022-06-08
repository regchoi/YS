package com.bh.mybatis.mybatis.mapper;

import com.bh.mybatis.mybatis.domain.MainBoard;
import com.bh.mybatis.mybatis.domain.SideBoard;

import java.util.List;

public interface BoardMapper {
    void createMainBoard(MainBoard mainBoard);
    void createSideBoard(SideBoard sideBoard);
    List<MainBoard> allMainBoard();
    MainBoard selectMainBoardById(int id);
    List<SideBoard> allSideBoard();
    List<SideBoard> selectSideBoardById(int id);
    SideBoard selectSideBoardByIdOne(int id);

    void deleteMainBoard(int id);
    void deleteSideBoard(int id);
    void editMainBoard(MainBoard mainBoard);

    void editSideBoard(SideBoard sideBoard);


}
