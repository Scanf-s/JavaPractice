package com.sullung2yo.rabbitmq.pub_sub_realtime_websocket_alarm;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // WebSocket 관련 Configuration

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 사용 시 STOMP를 사용하기 위한 엔드포인트 등록하는 메서드
        registry.addEndpoint("/ws") // WebSocket 연결을 위한 엔드포인트
                .setAllowedOriginPatterns("*") // CORS 설정
                .withSockJS(); // 클라이언트에서 SocketJS를 사용해서 WebSocket이 지원되지 않는 환경에서도 통신 가능하도록 설정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메세지 브로커 설정
        // 서버에서 클라이언트로 메세지를 전달할 때, 사용되는 엔드포인트를 지정하는 메서드

        // 클라이언트의 구독 경로 = /topic
        registry.enableSimpleBroker("/topic"); // 메모리 기반의 SimpleBroker를 사용

        // 서버의 Publish 경로 = /app
        registry.setApplicationDestinationPrefixes("/app");
    }
}
