package com.riding.springboot.controller;

import com.riding.springboot.domain.Hello;
import com.riding.springboot.domain.User;
import com.riding.springboot.reponse.JSONResponse;
import com.riding.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(path="/add") // Map ONLY GET Requests
    public JSONResponse<User> addNewUser (@RequestParam String name
            , @RequestParam String email) {
        User user = new User();

        JSONResponse<User> response = new JSONResponse<User>();

        user.setName(name);
        user.setEmail(email);
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
