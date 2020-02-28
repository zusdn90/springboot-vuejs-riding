package com.riding.springboot.advice;

import com.riding.springboot.advice.exception.CAuthenticationEntryPointException;
import com.riding.springboot.advice.exception.CEmailSigninFailedException;
import com.riding.springboot.advice.exception.CUserNotFoundException;
import com.riding.springboot.user.reponse.CommonResult;
import com.riding.springboot.user.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static jdk.nashorn.internal.runtime.ECMAErrors.getMessage;

@RequiredArgsConstructor
@RestControllerAdvice   //예외 발생 시 json형태로 결과를 반환하려면 @RestControllerAdvice를 클래스에 선언
public class ExceptionAdvice {
    private final ResponseService responseService;

//    @ExceptionHandler(Exception.class)  //Exception이 발생하면 해당 Handler로 처리하겠다고 명시하는 annotation
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //해당 Exception이 발생하면 Response에 출력되는 HttpStatus Code가 500으로 내려가도록 설정
//    protected CommonResult defaultException(HttpServletRequest request, Exception e){
//        return responseService.getFailResult();
//    }

    @ExceptionHandler(CUserNotFoundException.class)  //Exception이 발생하면 해당 Handler로 처리하겠다고 명시하는 annotation
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //해당 Exception이 발생하면 Response에 출력되는 HttpStatus Code가 500으로 내려가도록 설정
    protected CommonResult defaultException(HttpServletRequest request, Exception e){
        return responseService.getFailResult();
    }

    @ExceptionHandler(CEmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e) {
        return responseService.getFailResult();
    }
}
