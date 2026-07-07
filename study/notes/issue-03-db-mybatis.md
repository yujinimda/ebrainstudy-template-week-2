# Issue 03 — DB 연결 + MyBatis 설정

> 관련 PR: #__ · 참고: MyBatis Spring Boot Starter 문서

## 무엇을 만드나
스프링을 docker MySQL(3309)에 연결하고, SQL 실행 도구인 MyBatis를 붙인다.
실제 Mapper/도메인은 이슈 #4~#5에서. 이번엔 "연결 + 설정"까지.

## 알아야 할 개념
두 가지 별개 문제:
- **① DB 접속 (DataSource)** — 스프링이 MySQL에 붙으려면 아는 값 3종:
  - `spring.datasource.url` = `jdbc:mysql://localhost:3309/ebrainsoft_study` (주소:포트/DB이름)
  - `spring.datasource.username` = `ebsoft`, `spring.datasource.password` = `ebsoft`
  - 값 출처: `docker/docker-compose.yml`
- **② SQL 실행 (MyBatis)** — JDBC의 저수준 반복(커넥션 열고/쿼리/결과 꺼내고/닫고)을 대신.
  우리는 SQL만 쓰면 MyBatis가 실행 + 결과를 자바 객체로 변환. (Mapper는 #5에서)

의존성 (build.gradle):
- `implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4'`
  → 스프링 부트 BOM이 버전 관리를 안 해줘서 **버전 직접 명시** 필요.
- `runtimeOnly 'com.mysql:mysql-connector-j'`
  → 드라이버는 내 코드가 직접 호출 안 함(MyBatis/스프링이 씀) → 실행 시점에만 필요 → `runtimeOnly`.

## 1주차와 뭐가 다른가
- 1주차: JDBC로 직접 `DriverManager.getConnection(...)` 하고 커넥션을 손으로 관리.
- 2주차: 접속정보를 `application.properties`에 적으면 **스프링이 DataSource + 커넥션 풀(HikariCP)을 자동 구성**.

## 고민해봐야 할 것 / 함정
- **포트 `3309:3306`은 "다리"** — 왼쪽(3309)=호스트(내 맥에서 두드리는 문), 오른쪽(3306)=컨테이너 내부.
  스프링 URL엔 **왼쪽 3309**만. (3306 넣으면 안 붙음)
- URL의 `/` 뒤는 포트가 아니라 **DB 이름**(`ebrainsoft_study`).
- 스프링은 커넥션을 **실제 쓸 때까지 미룸(lazy)** → "부팅 성공"만으로 접속 성공을 100% 확신 못함.
  → 확인하려면 쿼리를 한 번 날려봐야 함.
- 이슈 #1에서 부팅 실패했던 이유 = 드라이버는 있는데 이 3종 세트가 없어서.

## 막히면 볼 것
- DB 띄우기: `cd docker && docker compose up -d`
- DB 확인: `docker exec study-db-week2 mysql -uebsoft -pebsoft ebrainsoft_study -e "SHOW TABLES;"`
- 연결 확인법: `main`에 임시 `CommandLineRunner`로 `DataSource.getConnection()` 후
  `SELECT COUNT(*) FROM category` 날려 출력 (확인용, 평소엔 안 둠).
  → 성공 시 로그: `HikariPool-1 - Start completed`, `category 행 수 = 5`

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
