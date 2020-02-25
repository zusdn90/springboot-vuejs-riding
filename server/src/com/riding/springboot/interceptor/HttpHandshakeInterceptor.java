package com.riding.springboot.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    /*
        웹소켓은 처음 connect 시점에 handshake 라는 작업이 수행됩니다.
        handshake 과정은 HTTP 통신을 기반으로 이루어지며 GET방식으로 통신을 하게 됩니다.
        이때, HTTP 요청 헤더의 Connection 속성은 Upgrade 로 되어야 합니다.

        웹소켓은 3번의 handshake 를 거쳐 연결을 확정
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        
        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        }
        
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
