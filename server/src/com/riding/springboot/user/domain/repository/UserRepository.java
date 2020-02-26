package com.riding.springboot.user.domain.repository;

import com.riding.springboot.user.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 하나의 테이블 개념
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    public List<User> findByUserIdLike(String userId);
}
