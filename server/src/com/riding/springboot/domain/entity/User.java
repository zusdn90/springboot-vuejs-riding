package com.riding.springboot.domain.entity;

import lombok.Data;

import javax.persistence.*;

// User.java는 하나의 테이블을 의미하며 @Entity 어노테이션을 사용하여 테이블임을 명시하고,
@Entity
@Data
public class User {
    @Id // 기본키임을 명시
    @GeneratedValue(strategy=GenerationType.AUTO)   //기본키값을 자동으로 증가하도록 설정
    private Integer id;
    private String userId;
    private String userEmail;
    private String userPwd;
    private String userPhoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
