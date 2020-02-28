package com.riding.springboot.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider<pulbic> { // JWT 토큰을 생성 및 검증하는 모듈

    @Value("${spring.jwt.secrect}")
    private String secrectKey;
    private long tokenValidMillisecond = 1000L * 60 *60;    // 1시간만 토큰 유효;
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secrectKey = Base64.getEncoder().encodeToString(secrectKey.getBytes());
    }

    // Jwt 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        // 토큰에 부가적인 정보 세팅
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)  //데이터
                .setIssuedAt(now)   // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 토큰 만료시간
                .signWith(SignatureAlgorithm.HS256, secrectKey) // 암호화 알고리즘, secret 값 세팅
                .compact();
    }

    // Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));

        //클라이언트가 리소스를 요청할 때 접근 권한이 없는 경우 기본적으로 로그인 폼으로 보내게 되는데 그 역할을 하는 필터(UsernamePasswordAuthenticationToken)
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secrectKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // Jwt 토큰의 유효성 + 만료일자 호가인
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secrectKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
