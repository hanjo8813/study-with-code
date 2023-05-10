package com.example.practicesocket.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * 클라이언트 도착지 url prefix
         * topic : 서버가 n명에게 메시지를 보내는 경우
         * queue : 서버가 한명에게 메시지를 보내는 경우
         */
        registry.enableSimpleBroker("/topic", "/queue");

        /**
         * 서버 도착지 url prefix
         * 엔드포인트 추가로 대체함
         */
//        registry.setApplicationDestinationPrefixes("/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .withSockJS();

    }
}
