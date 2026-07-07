# Issue 02 — Spring 핵심 개념: IoC/DI와 레이어드 아키텍처

> 관련 PR: #__ · 참고: 스프링 공식 문서 (Core / IoC Container)

## 무엇을 만드나
개념 이해가 목표. 눈으로 확인하려고 연습용 `com.study.board.hello` 패키지에
`GreetingService`(@Service) + `HelloController`(@RestController)를 만들어 DI를 직접 체험.
(진짜 게시판 시작하는 이슈 #5쯤 삭제 예정)

## 알아야 할 개념
- **IoC (제어의 역전)** = 객체를 만들고 연결하는 **주도권을 나 → 스프링**에 넘김. 원칙이라 눈엔 안 보임.
- **DI (의존성 주입)** = IoC를 실현하는 방법. 필요한 부품을 **생성자로 주입받음** (`new` 안 씀).
  - 실제 확인: `HelloController`가 `GreetingService`를 생성자로 받기만 함 → 스프링이 넣어줌.
- **컨테이너 (ApplicationContext)** = IoC를 실행하는 실체. `SpringApplication.run()`이 돌려주는 객체.
  - `context.getBeanDefinitionNames()`로 관리 중인 빈을 찍어보면 눈에 보임 (총 167개 중 우리 것 3개).
- **빈(Bean)** = 스프링이 만들어 관리하는 객체. `@Component/@Service/@Repository/@Controller` 표식이 붙은
  클래스를 **컴포넌트 스캔**이 찾아 등록. 이름은 클래스명 첫 글자 소문자 (GreetingService → greetingService).
- **싱글톤** = 빈은 기본적으로 앱당 1개, 공유. → 필드에 "요청/사용자 데이터" 저장 금지.
- **레이어드 아키텍처** = 요청이 단방향으로 흐름:
  `바깥 → @Controller(요청/응답) → @Service(로직) → @Mapper(SQL) → DB`. 위층이 아래층을 주입받아 씀.
- **@RestController** = @Controller + "메서드 `return`값을 그대로 HTTP 응답 본문으로". (Express의 `res.send` 역할)

## 1주차와 뭐가 다른가
- Servlet: 객체 생성/연결을 내가 `new`로 다 했고, 서블릿을 `web.xml`에 수동 등록.
- Spring: 애너테이션만 붙이면 **컨테이너가 객체를 만들어 주입**. 조립을 프레임워크가 대신.

## 고민해봐야 할 것 / 함정
- 클래스를 `com.study.board` **바깥**에 두면 컴포넌트 스캔 범위 밖 → 빈 등록 안 됨 → 404.
  (`@SpringBootApplication`이 있는 패키지 + 하위만 스캔) — "@Controller 붙였는데 404" 1순위 원인.
- 서비스 안에서 `new EmailSender()` 처럼 직접 생성 = DI 위반 (테스트 시 mock 교체 불가). 생성자로 받기.
- 대소문자 구분: `GreetingService`(클래스=대문자 시작) ≠ `greetingService`(변수/빈=소문자 시작).
- 테스트 코드에서 `new HelloController(fake)`는 정상 — 그땐 스프링이 없어 내가 컨테이너 역할.

## 막히면 볼 것
- 빈 목록 눈으로 보기: `main`에서 `var ctx = SpringApplication.run(...)` 후
  `ctx.getBeanDefinitionNames()` 반복 출력 (확인용, 평소엔 안 둠).
- 실행 확인: `./gradlew bootRun` → `curl localhost:8080/hello`
  → `안녕하세요! 이 문장은 GreetingService 빈이 만들었습니다 👋`

## 회고
<!-- PR 끝나고 채움: 새로 안 것 / 헷갈렸던 것 / 다음에 다르게 할 것 -->
-
