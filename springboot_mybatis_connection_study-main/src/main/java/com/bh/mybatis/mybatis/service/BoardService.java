package com.bh.mybatis.mybatis.service;

import com.bh.mybatis.mybatis.domain.MainBoard;
import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.domain.SideBoard;
import com.bh.mybatis.mybatis.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public List<MainBoard> allMainBoard(){
        return boardMapper.allMainBoard();
    }
    public MainBoard selectMainBoardById(int id) { return boardMapper.selectMainBoardById(id); }
    public List<SideBoard> selectSideBoardById(int id) { return boardMapper.selectSideBoardById(id); }
    public SideBoard selectSideBoardByIdOne(int id) { return  boardMapper.selectSideBoardByIdOne(id); }


    public void createBoard(MainBoard mainBoard, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        System.out.println(member);
        ModelMap model = new ModelMap();
        model.addAttribute("result", HttpStatus.OK);
        mainBoard.setMemberId(member.getMemberId());
        boardMapper.createMainBoard(mainBoard);
    }
    public void deleteMainBoard (int id) {
        boardMapper.deleteMainBoard(id);
    }
    public String deleteMainBoardById (int id, Model model, HttpServletRequest request) {
        deleteMainBoard(id);
        List<MainBoard> board = allMainBoard();
        request.getSession().setAttribute("message","삭제되었습니다");
        model.addAttribute("boardList",board);
        return "board/list";
    }
    public void editMainBoard(MainBoard mainBoard, Model model, HttpServletRequest request, int mainBoardId){
        HttpSession session = request.getSession();
        mainBoard.setMainboardId(mainBoardId);
        mainBoard.setModifiedDate(LocalDateTime.now());
        boardMapper.editMainBoard(mainBoard);
        Member member = (Member) session.getAttribute("member");
        model.addAttribute("result", HttpStatus.OK);
        List<MainBoard> board = allMainBoard();
        model.addAttribute("boardList",board);
        session.setAttribute("message","수정되었습니다");
    }

    public void createSideBoard(SideBoard sideBoard, HttpServletRequest request, int mainboardId) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        ModelMap model = new ModelMap();
        model.addAttribute("result", HttpStatus.OK);
        sideBoard.setMainboardId(mainboardId);
        sideBoard.setMemberId(member.getMemberId());
        boardMapper.createSideBoard(sideBoard);
    }
    public String deleteSideBoardById (int id, Model model, HttpServletRequest request) {
        int ids = selectSideBoardByIdOne(id).getMainboardId();
        boardMapper.deleteSideBoard(id);
        request.getSession().setAttribute("message","삭제되었습니다");
        model.addAttribute("mainboard", selectMainBoardById(ids));
        model.addAttribute("sideboard", selectSideBoardById(ids));
        return "board/detailList";
    }
//    public String editSideBoard(SideBoard sideBoard, Model model, HttpServletRequest request, int sideBoardId){
//        System.out.println(sideBoardId);
//        HttpSession session = request.getSession();
//        sideBoard.setSideboardId(sideBoardId);
//        sideBoard.setMainboardId(selectSideBoardByIdOne(sideBoardId).getMainboardId());
//        sideBoard.setMemberId(selectSideBoardByIdOne(sideBoardId).getMemberId());
//        sideBoard.setCreateDate(selectSideBoardByIdOne(sideBoardId).getCreateDate());
//        sideBoard.setModifiedDate(LocalDateTime.now());
//        System.out.println(sideBoard);
//        boardMapper.editSideBoard(sideBoard);
//        model.addAttribute("mainboard", selectMainBoardById(sideBoard.getMainboardId()));
//        model.addAttribute("sideboard", selectSideBoardById(sideBoard.getMainboardId()));
//        session.setAttribute("message","수정되었습니다");
//        return "board/detailList";
//    }
public void editSideBoard(SideBoard sideBoard, Model model, HttpServletRequest request, int sideBoardId){
    System.out.println(sideBoardId);
    HttpSession session = request.getSession();
    sideBoard.setSideboardId(sideBoardId);
    sideBoard.setMainboardId(selectSideBoardByIdOne(sideBoardId).getMainboardId());
    sideBoard.setMemberId(selectSideBoardByIdOne(sideBoardId).getMemberId());
    sideBoard.setCreateDate(selectSideBoardByIdOne(sideBoardId).getCreateDate());
    sideBoard.setModifiedDate(LocalDateTime.now());
    System.out.println(sideBoard);
    boardMapper.editSideBoard(sideBoard);
    model.addAttribute("mainboard", selectMainBoardById(sideBoard.getMainboardId()));
    model.addAttribute("sideboard", selectSideBoardById(sideBoard.getMainboardId()));
    session.setAttribute("message","수정되었습니다");
}

}
