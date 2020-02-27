package com.riding.springboot.user.controller;

import com.riding.springboot.user.domain.entity.User;
import com.riding.springboot.user.domain.repository.UserRepository;
import com.riding.springboot.user.reponse.CommonResult;
import com.riding.springboot.user.reponse.SingleResult;
import com.riding.springboot.user.service.ResponseService;
import com.riding.springboot.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1.SignUp User"})
@CrossOrigin("http://192.168.11.37:8081")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class SignUpController {

    private UserService userService;
    private final UserRepository userJpaRepo;
    private final ResponseService responseService; // 결과를 처리할 Service


    /**
     * 등록된 사용자 조회
     * @param
     * @return
     */
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.userSelect();
    }

    /**
     * 특정 사용자 조회
     * @param
     * @return
     */
    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
    @GetMapping(path="/user/{id}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long id) {
        return responseService.getSingleResult(userJpaRepo.findById(id).orElse(null));
    }

    /**
     * 회원가입(사용자 정보 저장)
     * @param user
     * @return
     */
    @ApiOperation(value = "회원 입력", notes = "회원 정보를 저장한다.")
    @ResponseBody
    @PostMapping("/user") //Client에서 json형태로 온 데이터를 받기 위해 @RequestBody 어노테이션 사용필요
    public SingleResult<User> saveUser (@ApiParam(value = "회원정보", required = true) @RequestBody User user) {

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    /**
     * 사용자 정보 수정
     * @param user
     * @return
     */
    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @ResponseBody
    @PutMapping("/user")
    public SingleResult<User> modifyUser (@ApiParam(value = "회원정보", required = true) @RequestBody User user) {

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    /**
     * 회원 삭제
     * @param id
     * @return
     */
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 저장한다.")
    @ResponseBody
    @DeleteMapping(value = "/user/{id}")
    public CommonResult deleteUser (@ApiParam(value = "회원번호", required = true) @PathVariable long id) {

        userService.userDelete(id);

        return responseService.getSuccessResult();
    }

    /**
     * 사용가능한 사용자 ID 정보 조회
     * @param userId
     * @return
     */
    @ApiOperation(value = "사용자 ID 확인", notes = "입력한 ID가 사용가능한 ID인지 체크한다.")
    @ResponseBody
    @GetMapping(path="/user/checkId")
    public CommonResult getUser(@RequestParam(value="userId") String userId) {
        List value = userJpaRepo.findByUserIdLike(userId);

        if(value.size() == 0){
            return responseService.getSuccessResult();
        }else {
            return responseService.getFailResult();
        }
    }

}
