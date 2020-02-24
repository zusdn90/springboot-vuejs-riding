package com.riding.springboot.controller;

import com.riding.springboot.domain.User;
import com.riding.springboot.reponse.JSONResponse;
import com.riding.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://192.168.11.37:8081")
@RestController
@RequestMapping(value = "/api/signUp")
public class SignUpController {

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
     * 사용가능한 사용자 ID 정보 조회
     * @param 'ID'
     * @return
     */
    @ResponseBody
    @GetMapping(path="/userCheckId")
    public JSONResponse<String> getUser(@RequestParam(value="userId") String userId) {
        JSONResponse<String> response = new JSONResponse<String>();

        List value = userService.userAvailableId(userId);

        if(value.size() == 0){
            response.setCode(0);
            response.setMsg("Available ID");
            response.setData("True");
        }else {
            response.setCode(1);
            response.setMsg("This is a duplicate ID");
            response.setData("False");
        }
        return response;
    }

    /**
     * 사용자 정보 전체 조회
     * @param 'null'
     * @return
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.userSelect();
    }

}
