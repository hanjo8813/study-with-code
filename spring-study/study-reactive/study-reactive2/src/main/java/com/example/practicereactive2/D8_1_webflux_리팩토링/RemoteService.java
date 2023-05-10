package com.example.practicereactive2.D8_1_webflux_리팩토링;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ----- 코드는 이전 강의와 동일 -----
 *
 * spring boot 2.0 부터 webflux 사용시 default 컨테이너는 netty
 * gradle 의존성에 걍 tomcat 추가하면 tomcat으로 뜸
 *  ㄴ 우선순위 : tomcat > netty
 *
 * 얘는 테스트를 위해 톰캣으로 띄움 (일반적인 서버라고 가정)
 */
@SpringBootApplication
public class RemoteService {

    @RestController
    public static class MyController {

        @GetMapping("/service1")
        public String service1(String req) throws InterruptedException {
            Thread.sleep(2000);
            return req + "/service1";
        }

        @GetMapping("/service2")
        public String service2(String req) throws InterruptedException {
            Thread.sleep(2000);
            return req + "/service2";
        }
    }

    public static void main(String[] args) {
        System.setProperty("SERVER.PORT", "8081");
        System.setProperty("server.tomcat.max-threads", "1000");
        SpringApplication.run(RemoteService.class, args);
    }
}
