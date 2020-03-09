package com.riding.springboot.user.domain.repository;

import com.riding.springboot.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 하나의 테이블 개념
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String email);

    // uid 및 provider정보로 소셜 회원정보 조회
    Optional<User> findByUidAndProvider(String uid, String provider);
}
