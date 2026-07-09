package com.study.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.board.domain.Board;

/**
 * 게시글 DB 접근 (이슈 #5). SQL은 resources/mapper/BoardMapper.xml 에 있다.
 *
 * @Mapper = MyBatis가 이 인터페이스를 스캔해서, XML의 SQL과 연결한 "구현체"를
 *           런타임에 자동 생성하고 빈으로 등록한다. (그래서 우리는 몸통을 안 쓴다)
 */
@Mapper
public interface BoardMapper {

    /** 게시글 전체 조회. XML의 <select id="findAll"> 와 연결됨. */
    List<Board> findAll();
}
