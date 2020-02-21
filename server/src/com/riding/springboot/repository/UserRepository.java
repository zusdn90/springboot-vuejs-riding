package com.riding.springboot.repository;

import com.riding.springboot.domain.User;
import org.springframework.data.repository.CrudRepository;

// 하나의 테이블 개념
public interface UserRepository extends CrudRepository<User, Integer>{
}
