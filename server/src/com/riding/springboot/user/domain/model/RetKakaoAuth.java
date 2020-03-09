package com.riding.springboot.user.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetKakaoAuth {     //카카오 token api 연동 시 맵핑을 위한 모델 생성
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
