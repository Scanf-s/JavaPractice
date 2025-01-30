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
        // STOMP를 사용하기 위한 엔드포인트 등록하는 메서드
        registry.addEndpoint("/ws") // WebSocket 연결을 위한 엔드포인트
                .setAllowedOriginPatterns("*") // CORS 설정
                .withSockJS(); // 클라이언트에서 SocketJS를 사용해서 WebSocket이 지원되지 않는 환경에서도 통신 가능하도록 설정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메세지 브로커 설정
        // 서버에서 클라이언트로 메세지를 전달할 때, 사용되는 엔드포인트를 지정하는 메서드

        registry.enableSimpleBroker("/topic"); // 메모리 기반의 SimpleBroker를 사용하고, 클라이언트가 구독 시 이 경로를 사용하도록 설정한다.
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메세지를 전송할 때 "/app" 경로로 보내도록 설정
    }
}
