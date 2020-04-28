package com.riding.springboot.controller.user;

import com.riding.springboot.advice.exception.CUserNotFoundException;
import com.riding.springboot.domain.entity.user.User;
import com.riding.springboot.domain.repository.UserRepository;
import com.riding.springboot.response.CommonResult;
import com.riding.springboot.response.ListResult;
import com.riding.springboot.response.SingleResult;
import com.riding.springboot.service.ResponseService;
import com.riding.springboot.service.user.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User API"})
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(path="/users")
    public @ResponseBody ListResult<User> getAllUsers() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    /**
     * 특정 사용자 조회
     * @param
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(id)로 회원을 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<User> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

    /**
     * 회원가입(사용자 정보 저장)
     * @param user
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원번호로 회원정보를 삭제한다.")
    @ResponseBody
    @DeleteMapping(value = "/user/{id}")
    public CommonResult deleteUser (@ApiParam(value = "회원번호", required = true) @PathVariable int id) {

        userService.userDelete(id);

        return responseService.getSuccessResult();
    }

    /**
     * 사용가능한 사용자 emil 정보 조회
     * @param id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "사용자 ID 확인", notes = "입력한 ID가 사용가능한 ID인지 체크한다.")
    @GetMapping(path="/user/checkId")
    public SingleResult<User> getUserCheck(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

}
