package com.riding.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Profile("stomp")
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    // handshake와 통신을 담당
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-chat").withSockJS();
    }

    // 메세지브로커를 등록하는 코드
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버에서 클라이언트로부터의 메세지를 받을 도착경로에 대한 prefix 를 설정
        registry.setApplicationDestinationPrefixes("/subscribe");
        // 메모리 기반 메세지 브로커가 해당 api를 구독하고 있는 클라이언트에게 메세지를 전달
        registry.enableSimpleBroker("/publish");
    }
}
