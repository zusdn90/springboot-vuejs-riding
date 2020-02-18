package com.riding.springboot.controller;

import com.riding.springboot.jpa.User;
import com.riding.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    // 사용자 이름을 입력받아 새로운 User를 생성하고 그 결과를 반환
    @PostMapping
    public User put(@RequestParam String name) {
        return userRepository.save(new User(name));
    }
    // READ
    // 모든 사용자 리스트를 반환
    @GetMapping
    public Iterable<User> list() {

        return userRepository.findAll();
    }
    /*
    // READ
    // 해당 ID의 사용자를 반환
    @GetMapping(value = "/{id}")
    public User findOne(@PathVariable Long id) {

        return userRepository.findOne(id);
    }
    // UPDATE
    // 해당 ID의 사용자 이름을 갱신한 뒤 그 결과를 반환
    @PutMapping(value = "/{id}")
    public User update(@PathVariable Long id, @RequestParam String name) {
        User user = userRepository.findOne(id);
        user.setName(name);
        return userRepository.save(user);
    }
    // DELETE
    // 해당 ID의 사용자를 삭제
    @DeleteMapping
    public void delete(@RequestParam Long id) {

        userRepository.delete(id);
    }
    */
}
