package com.bh.mybatis.mybatis.controller;

import com.bh.mybatis.mybatis.domain.MainBoard;
import com.bh.mybatis.mybatis.domain.Member;
import com.bh.mybatis.mybatis.domain.SideBoard;
import com.bh.mybatis.mybatis.dto.IdForm;
import com.bh.mybatis.mybatis.dto.JoinForm;
import com.bh.mybatis.mybatis.dto.LoginForm;
import com.bh.mybatis.mybatis.service.BoardService;
import com.bh.mybatis.mybatis.service.MemberService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final MemberService memberService;
    private final BoardService boardService;

    @Autowired
    public HomeController(MemberService memberService, BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @GetMapping("join")
    public String joinG(Model model, JoinForm joinForm) {
        model.addAttribute("joinForm", joinForm);
        return "board/join";
    }

    @PostMapping("join")
    public String joinP(Model model, LoginForm loginForm, JoinForm joinForm, HttpServletRequest request) {
        return memberService.joinMember(model, loginForm, joinForm, request);
    }
    @GetMapping
    public String loginG(Model model) {
        List<MainBoard> board = boardService.allMainBoard();
        model.addAttribute("boardList",board);
        return "board/list";
    }

    @GetMapping("create")
    public String boardCreate(Model model) {
        return "smarteditor/newPost";
    }

    @RequestMapping("savePost")
    public String savePost(HttpServletRequest request, MainBoard mainBoard) {
        boardService.createBoard(mainBoard, request);
        return "redirect:";
    }

    @GetMapping("board/{mainBoardId}")
    public String detailBoard(Model model, @PathVariable int mainBoardId){
        model.addAttribute("mainboard", boardService.selectMainBoardById(mainBoardId));
        model.addAttribute("sideboard", boardService.selectSideBoardById(mainBoardId));
        return "board/detailList";
    }

    @GetMapping("board/create/sideBoard/{mainBoardId}")
    public String detailBoardCreate(@PathVariable int mainBoardId, Model model, IdForm idForm){
        model.addAttribute("mainboard", boardService.selectMainBoardById(mainBoardId).getMainboardId());
        model.addAttribute("idForm", idForm);
        return "smarteditor/newSidePost";
    }

    @GetMapping("saveSidePost/{mainBoardId}")
    @ResponseBody
    public String saveSidePost(HttpServletRequest request, SideBoard sideBoard, @PathVariable("mainBoardId") int mainboardId) {
        boardService.createSideBoard(sideBoard, request, mainboardId);
        return "redirect:";
    }

    @GetMapping("board/delete/{mainBoardId}")
    public String deleteMain(@PathVariable("mainBoardId") int mainboardId, Model model, HttpServletRequest request){
        return boardService.deleteMainBoardById(mainboardId, model, request);
    }

    @GetMapping("board/edit/{mainBoardId}")
    public String editMain(@PathVariable("mainBoardId") int mainboardId, Model model, MainBoard mainBoard){
        mainBoard = boardService.selectMainBoardById(mainboardId);
        model.addAttribute("mainboard", mainBoard);
        return "smarteditor/newEditPost";
    }

    @GetMapping("savePost/edit/{mainBoardId}")
    @ResponseBody
    public void saveEditMainPost(HttpServletRequest request, Model model, MainBoard mainBoard, @PathVariable("mainBoardId") int mainboardId) {
        boardService.editMainBoard(mainBoard, model, request, mainboardId);
    }

    @GetMapping("board/side/delete/{sideBoardId}")
    public String deleteSide(@PathVariable("sideBoardId") int sideboardId, Model model, HttpServletRequest request){
        return boardService.deleteSideBoardById(sideboardId, model, request);
    }

    @GetMapping("board/side/edit/{sideBoardId}")
    public String editMain(@PathVariable("sideBoardId") int sideboardId, Model model, SideBoard sideBoard){
        sideBoard = boardService.selectSideBoardByIdOne(sideboardId);
        model.addAttribute("sideboard", sideBoard);
        return "smarteditor/newEditSidePost";
    }

    @GetMapping("savePost/side/edit/{sideBoardId}")
    @ResponseBody
    public void saveEditSidePost(HttpServletRequest request, Model model, SideBoard sideBoard, @PathVariable("sideBoardId") int sideboardId) {
        boardService.editSideBoard(sideBoard, model, request, sideboardId);
    }

    @GetMapping("board/choose/sideBoard/{mainBoardId}")
    public String chooseSideBoard(@PathVariable int mainBoardId, Model model){
        model.addAttribute("mainboardId", mainBoardId);
        model.addAttribute("sideboard", boardService.selectSideBoardById(mainBoardId));
        return "board/detailChooseList";
    }
}