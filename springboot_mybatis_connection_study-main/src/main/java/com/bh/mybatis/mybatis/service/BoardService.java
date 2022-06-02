package com.bh.mybatis.mybatis.service;

import com.bh.mybatis.mybatis.domain.MainBoard;
import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.dto.JoinForm;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.mapper.BoardMapper;
import com.bh.mybatis.mybatis.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public void createBoard(MainBoard mainBoard) {
        boardMapper.createMainBoard(mainBoard);
    }


}
