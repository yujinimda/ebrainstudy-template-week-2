# Issue 01 — Spring Initializr로 프로젝트 셋업

> 관련 PR: #__ · 참고: start.spring.io / Spring Boot 3.5.x

## 무엇을 만드나
나머지 14개 이슈가 올라갈 Spring Boot 프로젝트 골격을 만든다. Spring Initializr로
`com.study.board` 패키지, Gradle, Java 21, Spring Boot 3.5.16 프로젝트를 생성하고
리포 루트에 배치. 아직 컨트롤러/DB 없이 "부팅만 되는" 최소 상태.

## 알아야 할 개념
- **Spring Initializr** = 스캐폴딩 도구 (프론트의 `npm create vite@latest`). 옵션 골라 골격 zip 생성.
- **의존성 3개** (이번 이슈):
  - `Spring Web` — 내장 Tomcat + MVC (웹서버 켜기). → `build.gradle`의 `spring-boot-starter-web`
  - `Thymeleaf` — 서버사이드 HTML 템플릿 엔진 (JSP 자리). → `spring-boot-starter-thymeleaf`
  - `Lombok` — getter/setter 등 보일러플레이트 자동 생성. → `compileOnly` + `annotationProcessor`
  - MyBatis·MySQL은 **일부러 뺌** → 이슈 #3에서 추가.
- **`@SpringBootApplication`** (`BoardApplication.java`) — 앱 시작점 표시 + `com.study.board` 아래를
  전부 스캔해 컴포넌트 자동 등록. → 앞으로 만들 컨트롤러/서비스는 이 패키지 아래 둬야 인식됨.
- **`gradlew` (Gradle Wrapper)** — 프로젝트에 박힌 실행기. 전역 Gradle 설치 없이도 필요한 버전(8.14.5)을
  알아서 받아 씀 (프론트 `npx` 감각). 팀원 간 빌드 버전 고정 장치.
- **파일 지도**: `build.gradle`≈package.json, `gradlew`≈npx, `application.properties`≈.env,
  `BoardApplication.java`≈진입점, `templates/`·`static/`≈뷰/정적자원.

## 1주차와 뭐가 다른가
- 1주차(Servlet/JSP): 톰캣을 따로 설치/설정하고 `web.xml`로 서블릿을 직접 등록했다.
- 2주차(Spring Boot): **내장 Tomcat**이 의존성에 포함돼 `main()` 한 방으로 서버가 뜬다.
  설정(`web.xml` 등)은 스프링이 **자동 구성(auto-configuration)**으로 대신 처리.

## 고민해봐야 할 것
- MySQL 드라이버를 미리 넣으면 왜 부팅이 실패하나 → 드라이버가 있으면 스프링이 DataSource 접속을
  시도하는데 접속 설정이 없어서 `Failed to configure a DataSource`로 죽는다. 그래서 DB 붙이는
  이슈 #3에서 드라이버+설정을 함께 넣는다.
- 프로젝트를 `board/` 하위가 아니라 **리포 루트**에 둔 이유: 이 리포 자체가 게시판 프로젝트라
  루트에서 `./gradlew`가 바로 돌고 IDE로 열기 자연스럽다. 두 `.gitignore`는 병합함.

## 막히면 볼 것
- 빌드/테스트: `./gradlew build` → `BUILD SUCCESSFUL`
- 실행: `./gradlew bootRun` → 로그에 `Started BoardApplication`, `Tomcat started on port 8080`
- 확인: 브라우저 `localhost:8080` → **Whitelabel Error Page(404)** = 서버 정상 (컨트롤러가 없을 뿐).
  `curl`로 요청하면 같은 404를 JSON으로 응답.
- 서버 끄기: 터미널에서 `Ctrl + C`

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
