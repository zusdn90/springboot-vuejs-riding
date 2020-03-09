package com.riding.springboot.user.controller;

import com.riding.springboot.advice.exception.CEmailSigninFailedException;
import com.riding.springboot.advice.exception.CUserNotFoundException;
import com.riding.springboot.config.security.JwtTokenProvider;
import com.riding.springboot.user.domain.entity.User;
import com.riding.springboot.user.domain.model.KakaoProfile;
import com.riding.springboot.user.domain.repository.UserRepository;
import com.riding.springboot.user.reponse.CommonResult;
import com.riding.springboot.user.reponse.SingleResult;
import com.riding.springboot.user.service.ResponseService;
import com.riding.springboot.user.service.social.KakaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Api(tags = "3. Sign")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class SignInController {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;


    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {


        User user = userRepository.findByUid(id).orElseThrow(CEmailSigninFailedException::new);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));

    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> signinKakao(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        User user = userRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider).orElseThrow(CUserNotFoundException::new);
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));

    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다.")
    @GetMapping(value = "/signup")
    public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                               @ApiParam(value = "이름", required = true) @RequestParam String name,
                               @ApiParam(value = "핸드폰번호", required = true) @RequestParam String phoneNumber) {

        userRepository.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
    @PostMapping(value="/signup/{provider}")
    public CommonResult signupKakao(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                    @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken,
                                    @ApiParam(value = "이름", required = true) @RequestParam String name) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        Optional<User> user = userRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider);

        if(user.isPresent())
            throw new CUserNotFoundException();

        userRepository.save(User.builder()
                    .uid(String.valueOf(profile.getId()))
                    .provider(provider)
                    .name(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build());

        return responseService.getSuccessResult();


    }
}
