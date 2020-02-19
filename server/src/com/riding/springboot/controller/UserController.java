package com.riding.springboot.controller;

import com.riding.springboot.domain.User;
import com.riding.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.userInsert(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.userSelect();
    }

}
