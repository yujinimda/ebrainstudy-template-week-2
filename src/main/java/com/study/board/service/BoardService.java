package com.study.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.board.domain.Board;
import com.study.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> getBoardList() {
        return boardMapper.findAll();
    }

    /** 제목 검색. keyword가 비어있으면 전체 목록. */
    public List<Board> search(String keyword) {
        return boardMapper.search(keyword);
    }
}
