package com.study.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.board.domain.Board;
import com.study.board.service.BoardService;

import lombok.RequiredArgsConstructor;

/**
 * 게시글 화면 컨트롤러 (이슈 #5).
 *
 * @Controller = @RestController와 달리, 메서드가 return하는 문자열을 "응답 본문"이 아니라
 *               "보여줄 뷰(HTML 템플릿)의 이름"으로 해석한다.
 */
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;   // 주입

    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.getBoardList();  // Service → Mapper → DB
        model.addAttribute("boards", boards);              // 화면에 넘길 데이터를 "boards"란 이름으로 담음
        return "board/list";                               // templates/board/list.html 을 그려라
    }
}
