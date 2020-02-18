package com.riding.springboot.repository;

import com.riding.springboot.jpa.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
