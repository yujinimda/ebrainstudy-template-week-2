package com.study.board.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IoC/DI 연습용 컨트롤러 (이슈 #2). 진짜 게시판 시작하면 지울 예정.
 *
 * @RestController = @Controller + "리턴값을 그대로 HTTP 응답 본문으로 보내라".
 *                   (지금은 Thymeleaf 없이 텍스트만 볼 거라 이걸 씀. 뷰는 나중 이슈)
 */
@RestController
public class HelloController {

    // 생성자 주입: "나 GreetingService 필요해"라고 선언만 하고, 실제 객체는 스프링이 넣어준다.
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @GetMapping("/hello")   // 브라우저로 GET /hello 요청이 오면 이 메서드 실행
    public String hello() {
        // 위에서 주입받은 서비스한테 일을 시킨다 (내가 new 하지 않았는데 쓸 수 있음)
        return greetingService.greeting();
    }
}
