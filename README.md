# eBrainSoft 스터디 2주차 — Spring Boot 게시판

1주차([JSP/Servlet 게시판](https://github.com/yujinimda/ebrainstudy-template-week-1))과 **같은 게시판**을 Spring Boot로 다시 만든다.
같은 기능을 다른 도구로 만들면서 "스프링이 뭘 대신해 주는지"를 몸으로 이해하는 게 목표.

## 기술 스택

| 구분 | 사용 기술 |
|---|---|
| 백엔드 | Spring Boot 3.x, MyBatis |
| 뷰 | Thymeleaf |
| DB | MySQL 8.0 (docker, 포트 **3309**) |
| 편의 도구 | Lombok, MapStruct |
| 빌드 | Gradle |

## 학습 루틴

이슈 하나당: **개념 설명 → 퀴즈 → 핵심 로직은 내가 직접(TODO(human)) → 리뷰 → 노트 → PR**
자세한 규칙은 [study/00-learning-workflow.md](study/00-learning-workflow.md).

## 진행 현황

- [x] #1 Spring Initializr로 프로젝트 셋업
- [x] #2 Spring 핵심 개념: IoC/DI와 레이어드 아키텍처
- [x] #3 DB 연결 + MyBatis 설정
- [x] #4 도메인/DTO 클래스 + Lombok
- [x] #5 목록 조회 (Mapper → Service → Controller → Thymeleaf)
- [ ] #6 검색 (MyBatis 동적 SQL)
- [ ] #7 페이징
- [ ] #8 보기 화면 + 조회수 증가
- [ ] #9 등록 + 유효성 검증 (Bean Validation)
- [ ] #10 비밀번호 확인 레이어 + 수정
- [ ] #11 삭제
- [ ] #12 댓글 (등록 + 목록)
- [ ] #13 파일 업로드/다운로드
- [ ] #14 (리팩터) MapStruct 매핑 도입
- [ ] #15 (리팩터) 공통 예외 처리 + 트랜잭션

## DB 띄우기

```bash
cd docker
docker compose up -d
# 접속: localhost:3309 / ebsoft / ebsoft / ebrainsoft_study
```

최초 기동 시 `docker/initdb/`의 스키마·시드가 자동 적용된다. (1주차와 같은 스키마)

## 폰에서 복습

- 이슈 본문과 `study/notes/`가 전부 이 리포에 있어서 폰 GitHub 앱으로 바로 읽을 수 있다.
- 폰 Claude 앱에서 GitHub 연동으로 이 리포를 연결하면 노트 기반으로 질문/복습을 이어갈 수 있다.
