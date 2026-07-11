package com.study.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.board.domain.Board;
import com.study.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /** 게시글 목록 + 제목 검색. keyword가 없으면 전체 목록. */
    @GetMapping("/boards")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Board> boards = boardService.search(keyword);
        model.addAttribute("boards", boards);
        model.addAttribute("keyword", keyword);   // 검색 후 입력창에 검색어 유지
        return "board/list";
    }
}
