package com.riding.springboot.user.controller;

import com.riding.springboot.advice.exception.CEmailSigninFailedException;
import com.riding.springboot.config.security.JwtTokenProvider;
import com.riding.springboot.user.domain.entity.User;
import com.riding.springboot.user.domain.repository.UserRepository;
import com.riding.springboot.user.reponse.CommonResult;
import com.riding.springboot.user.reponse.SingleResult;
import com.riding.springboot.user.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = "3. Sign")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class SignInController {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;


    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {


        User user = userRepository.findByUserEmail(id).orElseThrow(CEmailSigninFailedException::new);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));

    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @GetMapping(value = "/signup")
    public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name) {

        userRepository.save(User.builder()
                .userEmail(id)
                .userPwd(passwordEncoder.encode(password))
                .userName(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }
}
