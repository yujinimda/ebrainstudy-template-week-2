# Issue 06 — 제목 검색 (MyBatis 동적 SQL)

> 관련 PR: #22 · 참고: MyBatis 동적 SQL(`<if>`/`<where>`), PreparedStatement 바인딩

## 무엇을 만드나
목록에 **제목 검색**을 얹는다. `/boards?keyword=자바` 처럼 검색어를 받아
`title LIKE '%자바%'` 로 거른다. 검색어가 없으면 지금처럼 전체가 나온다.

## 알아야 할 개념
- **동적 SQL**: 상황(검색어 유무)에 따라 SQL 모양이 달라져야 한다. 이 분기를 자바 문자열
  이어붙이기 대신 MyBatis XML 태그로 처리한다.
- **`<if test="...">`**: 조건이 참일 때만 안쪽 SQL을 포함. `keyword != null and keyword != ''`
  로 "검색어가 실제로 있을 때만" WHERE를 붙인다. → `BoardMapper.xml`의 `<select id="search">`.
  - 검색어가 없으면 `<if>`가 통째로 빠져서 `SELECT * FROM board ORDER BY ...` = 전체 조회.
    그래서 "검색"과 "전체 목록"을 메서드 하나(`search`)로 처리한다.
- **`#{}` vs `${}`**: `#{keyword}`는 PreparedStatement의 `?`로 값이 안전하게 바인딩(인젝션 방어).
  `${}`는 문자열을 그대로 치환해서 위험 → 검색어엔 항상 `#{}`.
- **`LIKE CONCAT('%', #{keyword}, '%')`**: `'%#{keyword}%'` 처럼 리터럴 안에는 바인딩이 안 된다.
  그래서 DB의 `CONCAT`으로 검색어 앞뒤에 `%`를 이어붙인다.
- **`@RequestParam(required = false)`**: 쿼리스트링 `?keyword=` 값을 받되, 없어도 에러 없이 null.
  → 그냥 `/boards`로 들어와도 전체 목록.

## 1주차와 뭐가 다른가
- 1주차(JSP/Servlet): 자바 코드에서 `if (keyword != null) sql += " WHERE ..."` 로 SQL 문자열을
  손으로 이어붙이고 `pstmt.setString(1, ...)` 으로 값 바인딩.
- 2주차: 그 분기 로직이 XML `<if>` 로, 값 바인딩이 `#{}` 로 넘어간다. 자바 코드는 검색어를
  넘기기만 한다.

## 고민해봐야 할 것 / 함정
- `!= null` 만 검사하면 검색창을 비우고 눌렀을 때 들어오는 빈 문자열(`""`)을 못 걸러서
  `LIKE '%%'` 가 된다(= 전체지만 의도치 않게 조건이 붙음). `!= ''` 까지 같이 본다.
- `LIKE '%#{keyword}%'` 는 동작 안 함 → `CONCAT` 사용.
- 검색 타입(제목/작성자/내용)으로 **컬럼명을 바꿔야** 할 땐 `#{}`(값 바인딩)로는 안 되고
  `<if>` 로 조건을 나눠 쓴다. 이번엔 제목만 → 다음에 필요하면 확장.

## 막히면 볼 것
- 실행: `./gradlew bootRun` → `http://localhost:8080/boards`, 검색창에 "자바"/"MySQL"
- SQL: `src/main/resources/mapper/BoardMapper.xml` 의 `<select id="search">`
- DB 확인: `docker exec study-db-week2 mysql --default-character-set=utf8mb4 -uebsoft -pebsoft ebrainsoft_study -e "SELECT board_id, title FROM board;"`

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
