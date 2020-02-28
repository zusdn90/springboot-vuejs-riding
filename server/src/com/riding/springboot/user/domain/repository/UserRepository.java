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
public interface UserRepository extends CrudRepository<User, Integer> {
    public List<User> findByUserEmailLike(String email);
    Optional<User> findByUserEmail(String email);
}
