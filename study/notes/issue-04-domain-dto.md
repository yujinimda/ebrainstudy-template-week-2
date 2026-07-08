# Issue 04 — 도메인/DTO 클래스 + Lombok

> 관련 PR: #__ · 참고: Lombok 문서, Java 타입(원시 vs 래퍼)

## 무엇을 만드나
board 테이블의 한 행을 담을 `Board` 도메인 클래스(`com.study.board.domain.Board`)를 만든다.
Lombok으로 getter/setter/toString을 자동 생성. DTO는 필요한 기능(검색 #6, 등록 #9 등)에서 그때 만듦.

## 알아야 할 개념
- **도메인 클래스** = DB 테이블의 한 행 = 자바 객체 하나. 층 사이로 이 타입이 오간다.
  - 컬럼 ↔ 필드 매핑, snake_case(`view_count`) ↔ camelCase(`viewCount`).
- **DB 타입 → 자바 타입**: `VARCHAR`→`String`, `INT`→`int`/`Integer`, `DATETIME`→`LocalDateTime`, `BIGINT`→`Long`.
- **원시 vs 래퍼** (자바 특유, 중요):
  - `int`(원시) = null 불가, 초기값 **0**. NOT NULL 컬럼에.
  - `Integer`(래퍼) = null 가능, 초기값 **null**. NULL 가능 컬럼 / AUTO_INCREMENT PK(insert 전 null)에.
  - 실제 확인: 세팅 안 한 `boardId`(Integer)=null, `categoryId`(int)=0 로 찍힘.
- **Lombok**: `@Getter @Setter @ToString` → 컴파일 때 `getXxx()/setXxx()/toString()` 자동 생성.
  → 반복 보일러플레이트 제거. (getter/setter를 한 줄도 안 썼는데 동작함을 확인)
  - MyBatis는 기본 no-arg 생성자로 객체를 만들어 setter로 값을 채움 (생성자 안 쓰면 자바가 자동 제공).
- **도메인 vs DTO**: "DB 모양" ≠ "화면/요청이 원하는 모양". password 노출 방지, 등록폼(id 없음),
  검색요청(키워드+페이지) 등은 별도 DTO로.

## 1주차와 뭐가 다른가
- 1주차: getter/setter를 직접 다 타이핑하거나 IDE 생성 → 필드 바뀔 때마다 관리.
- 2주차: Lombok 애너테이션 한두 줄로 대체. 필드만 관리하면 됨.

## 고민해봐야 할 것 / 함정
- NULL 가능 컬럼을 `int`로 받으면 DB의 null이 0으로 둔갑 → NULL 컬럼엔 반드시 래퍼.
- `LocalDateTime` 철자 (Date + Time, 대문자 D·T). 자바는 대소문자 구분 → 오타 시 컴파일 에러.
- `password`는 문자열(`String`)! 숫자 아님 (VARCHAR).

## 막히면 볼 것
- 스키마: `docker/initdb/01-schema.sql` 의 `CREATE TABLE board`
- Lombok 동작 확인: 임시 `CommandLineRunner`에서 `new Board()` 후 `setTitle()/getTitle()/toString()` 출력
  (확인용, 평소엔 안 둠) → getter/setter를 안 썼는데 동작하면 Lombok OK.

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
