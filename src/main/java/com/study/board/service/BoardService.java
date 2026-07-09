package com.study.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.board.domain.Board;
import com.study.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

/**
 * 게시글 비즈니스 로직 (이슈 #5). 지금은 매퍼를 그대로 호출만 하지만,
 * 나중에 조회수 증가·검증 같은 "판단"이 여기 쌓인다.
 *
 * @RequiredArgsConstructor = final 필드(boardMapper)를 받는 생성자를 Lombok이 자동 생성.
 *                            → 생성자 주입을 손으로 안 써도 됨 (이슈 #2에서 손으로 했던 그것).
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;   // 스프링이 주입해줌

    /** 게시글 목록 조회. */
    public List<Board> getBoardList() {
        return boardMapper.findAll();
    }
}
