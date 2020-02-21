package com.riding.springboot.controller;

import com.riding.springboot.domain.Hello;
import com.riding.springboot.domain.User;
import com.riding.springboot.reponse.JSONResponse;
import com.riding.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@CrossOrigin("http://192.168.11.37:8081")
@RestController
@RequestMapping(value = "/api/common")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원가입(사용자 정보 저장)
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/userSignUp")
    public JSONResponse<User> addNewUser (@RequestBody User user) {
        JSONResponse<User> response = new JSONResponse<User>();

        userService.userInsert(user);

        response.setCode(0);
        response.setMsg("Success");
        response.setData(user);

        return response;
    }

    /**
     * 사용자 ID 중복체크
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/userCheck")
    public JSONResponse<User> getUserDuplicateCheck (@RequestBody User user) {
        JSONResponse<User> response = new JSONResponse<User>();

        Iterable<User> userInfo = getAllUsers();

        response.setCode(0);
        response.setMsg("사용 가능한 ID 입니다.");
        response.setData(user);

        return response;
    }

    /**
     * 사용자 정보 전체 조회
     * @param "null"
     * @return
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.userSelect();
    }

}
