package com.riding.springboot.controller;

import com.riding.springboot.domain.Hello;
import com.riding.springboot.domain.User;
import com.riding.springboot.reponse.JSONResponse;
import com.riding.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/common")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin("http://192.168.11.37:8081")
    @ResponseBody
    @PostMapping("/userSignUp")
    public JSONResponse<User> addNewUser (@RequestBody User user) {
        JSONResponse<User> response = new JSONResponse<User>();

        userService.userInsert(user);

        response.setCode(0);
        response.setMsg("응답코드가 0이면 Success");
        response.setData(user);

        return response;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.userSelect();
    }

}
