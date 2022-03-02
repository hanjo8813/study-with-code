package com.example.springsocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    /**
     * @MessageMapping : 클라이언트 -> 서버로 요청하는 주소
     * @SendTo : 서버 -> 클라언트로 보내는 주소
     */
    @MessageMapping("connect")
    @SendTo("/topic/connect")
    public String connect(String message){
        System.out.println(message);
        return message + " 받았고 연결완료";
    }
}
