package com.riding.springboot.domain.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riding.springboot.domain.entity.common.CommonDateEntity;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Entity                 // entity는 DB TABLE 간의 구조와 관계를 JPA가 요구하는 형태로 만든 model
@Getter                 // user 필드값의 getter를 자동으로 생성
@NoArgsConstructor      // 인자없는 생성자를 자동으로 생성합니다
@AllArgsConstructor     // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "user")   // 'user' 테이블과 맵핑됨
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  //Post Entity에서 User와의 관계를 Json으로 변환 시 오류 방지를 위한 코드
@Proxy(lazy = false)    // User클래스는 다른 class에서 연관관계 매핑을 통해 Lazy로딩되므로 캐싱시 문제가 발생되지 않도록 Proxy false 설정을 한다.
public class User extends CommonDateEntity implements UserDetails {
    @Id // 기본키임을 명시
    @GeneratedValue(strategy=GenerationType.AUTO)   //기본키값을 자동으로 증가하도록 설정
    private long msrl; //회원번호

    @Column(nullable = false, unique = true, length = 50)
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    private String phoneNumber;

    @Column(length = 100)
    private String provider;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
