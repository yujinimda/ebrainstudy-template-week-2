# Issue 05 — 목록 조회 (Mapper → Service → Controller → Thymeleaf)

> 관련 PR: #21 · 참고: MyBatis 매퍼, Thymeleaf, Spring MVC Model

## 무엇을 만드나
첫 완성 기능. `/boards` 요청 → DB의 게시글 전체를 조회 → HTML 표로 렌더링.
4개 층(Mapper/Service/Controller/View)을 처음으로 관통시킨다.

## 알아야 할 개념
- **흐름(양방향)**: 요청은 위→아래(Controller→Service→Mapper), 데이터는 아래→위로 올라옴.
  각 층은 한 가지만: Controller=요청/응답, Service=로직, Mapper=SQL.
- **MyBatis Mapper = 인터페이스 + XML**:
  - `@Mapper` 인터페이스에 메서드 "선언"만 (`List<Board> findAll();`, 몸통 없음).
  - `resources/mapper/BoardMapper.xml`에 SQL. `namespace`=인터페이스 FQN, `<select id>`=메서드명으로 연결.
  - MyBatis가 이 둘을 이어 **구현체를 런타임 자동 생성** → 우리는 SQL만 씀. (IoC와 같은 사상)
- **map-underscore-to-camel-case** (application.properties):
  - `board_id`(snake) → `boardId`(camel) 자동 매핑. 없으면 필드가 null로 들어옴.
- **@RequiredArgsConstructor** (Lombok): `final` 필드를 받는 생성자 자동 생성 → 생성자 주입을 손으로 안 씀.
  (이슈 #2에서 손으로 쓴 그것의 자동화 버전)
- **@Controller vs @RestController**: @Controller는 return 문자열을 "뷰 이름"으로 해석(HTML 렌더),
  @RestController는 "응답 본문"으로. 목록은 화면이라 @Controller.
- **Model**: `model.addAttribute("boards", list)` → 뷰로 데이터 전달. 뷰에서 `${boards}`로 접근.
- **Thymeleaf**: `xmlns:th` 선언 / `th:each="b : ${boards}"`(반복) / `th:text="${b.title}"`(출력, getter 호출).

## 1주차와 뭐가 다른가
- 1주차: JSP에서 스크립틀릿/JSTL로 ResultSet 순회, 커넥션 수동.
- 2주차: MyBatis가 SQL 실행+객체 변환, 스프링이 Model로 뷰에 전달, Thymeleaf가 순수 HTML로 렌더.

## 고민해봐야 할 것 / 함정
- SQL은 DB 테이블 이름(소문자 `board`), 자바 클래스(`Board`)와 구분. SELECT는 컬럼 snake_case.
- `SELECT *`의 `*`은 "모든 컬럼"(ALL 아님). 최신순 = `ORDER BY board_id DESC`.
- map-underscore-to-camel-case 빠지면 조회는 되는데 필드가 전부 null.
- 한글을 SQL 조건으로 쓸 때 charset 안 맞으면 WHERE가 안 걸림 → `--default-character-set=utf8mb4`.

## 막히면 볼 것
- 실행: `./gradlew bootRun` → 브라우저 `http://localhost:8080/boards`
- Mapper XML: `src/main/resources/mapper/BoardMapper.xml`
- DB 확인: `docker exec study-db-week2 mysql --default-character-set=utf8mb4 -uebsoft -pebsoft ebrainsoft_study -e "SELECT * FROM board;"`

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
