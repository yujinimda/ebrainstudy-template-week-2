package com.study.board.hello;

import org.springframework.stereotype.Service;

/**
 * IoC/DI 연습용 서비스 (이슈 #2). 진짜 게시판 시작하면 지울 예정.
 *
 * @Service = "스프링아, 이 클래스를 빈으로 등록해서 네가 관리해줘" 라는 표식.
 *            → 앱이 뜰 때 컴포넌트 스캔에 걸려 컨테이너에 담긴다. (이름: greetingService)
 */
@Service
public class GreetingService {

    public String greeting() {
        return "안녕하세요! 이 문장은 GreetingService 빈이 만들었습니다 👋";
    }
}
