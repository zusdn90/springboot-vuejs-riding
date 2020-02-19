package com.riding.springboot.repository;

import com.riding.springboot.domain.User;
import org.springframework.data.repository.CrudRepository;

// 하나의 테이블이라고 생각하면 쉽다.
public interface UserRepository extends CrudRepository<User, Integer>{
}
