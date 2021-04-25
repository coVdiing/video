package com.vi.business.controller;

import com.vi.server.dto.ResponseDto;
import com.vi.server.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidateException.class)
    @ResponseBody
    public ResponseDto validateExceptionHandler(ValidateException e) {
        log.warn(e.getMessage());
        return ResponseDto.error().message("请求参数有误");
    }
}
