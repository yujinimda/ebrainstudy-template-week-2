package com.study.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * board 테이블의 한 행(게시글 하나)을 담는 도메인 클래스. (이슈 #4)
 *
 * @Getter @Setter = Lombok이 모든 필드의 getBoardId()/setTitle() 등을 컴파일 때 자동 생성.
 * @ToString        = 로그 찍을 때 보기 좋게 toString()도 자동 생성.
 * (생성자를 하나도 안 쓰면 자바가 기본 no-arg 생성자를 자동 제공 → MyBatis가 이걸로 객체를 만듦)
 */
@Getter
@Setter
@ToString
public class Board {

    // ── 예시 (내가 채워둠) ─────────────────────────────────────────
    // board_id INT, AUTO_INCREMENT → INSERT 전엔 값이 없음(null) → 래퍼 Integer
    private Integer boardId;
    // title VARCHAR(100) NOT NULL → String
    private String title;

    // 나머지 필드 (직접 채운 부분)
    private int categoryId;            // category_id INT NOT NULL
    private String content;            // content VARCHAR(2000) NOT NULL
    private String writer;             // writer VARCHAR(20) NOT NULL
    private String password;           // password VARCHAR(100) NOT NULL (지금은 평문)
    private int viewCount;             // view_count INT NOT NULL DEFAULT 0
    private LocalDateTime createdAt;   // created_at DATETIME NOT NULL
    private LocalDateTime updatedAt;   // updated_at DATETIME NULL (수정 전엔 null)

}
