package com.riding.springboot.user.domain.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Entity                 // entity는 DB TABLE 간의 구조와 관계를 JPA가 요구하는 형태로 만든 model
@Getter                 // user 필드값의 getter를 자동으로 생성
@NoArgsConstructor      // 인자없는 생성자를 자동으로 생성합니다
@AllArgsConstructor     // 인자를 모두 갖춘 생성자를 자동으로 생성합니다.
@Table(name = "user")   // 'user' 테이블과 맵핑됨
public class User {
    @Id // 기본키임을 명시
    @GeneratedValue(strategy=GenerationType.AUTO)   //기본키값을 자동으로 증가하도록 설정
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    private String userId;

    @Column(nullable = false, unique = true, length = 30)
    private String userEmail;

    @Column(nullable = false, length = 100)
    private String userName;

    private String userPwd;
    private String userPhoneNumber;

}
