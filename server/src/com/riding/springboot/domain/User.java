package com.riding.springboot.domain;

import javax.persistence.*;

// User.java는 하나의 테이블을 의미하며 @Entity 어노테이션을 사용하여 테이블임을 명시하고,
@Entity
public class User {
    @Id // 기본키임을 명시
    @GeneratedValue(strategy=GenerationType.AUTO)   //기본키값을 자동으로 증가하도록 설정
    private Integer id;

    private String name;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
