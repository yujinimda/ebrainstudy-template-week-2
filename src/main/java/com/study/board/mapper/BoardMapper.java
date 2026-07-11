package com.study.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.board.domain.Board;

@Mapper
public interface BoardMapper {

    List<Board> findAll();

    /** 제목 검색. keyword가 비어있으면 조건 없이 전체 조회. */
    List<Board> search(@Param("keyword") String keyword);
}
