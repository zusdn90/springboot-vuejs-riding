package com.riding.springboot.repository;

import com.riding.springboot.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 하나의 테이블 개념
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    public List<User> findByUserIdLike(String userId);
}
