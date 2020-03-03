package com.riding.springboot.user.controller.exception;

import com.riding.springboot.advice.exception.CAuthenticationEntryPointException;
import com.riding.springboot.user.reponse.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() throws CAuthenticationEntryPointException {
        throw new CAuthenticationEntryPointException();
    }
}
