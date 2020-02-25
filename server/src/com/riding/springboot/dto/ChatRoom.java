package com.riding.springboot.dto;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {
    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 생성
    public static ChatRoom create(String name) {
        ChatRoom created = new ChatRoom();
        created.id = UUID.randomUUID().toString();
        created.name = name;

        return created;
    }
}
