package com.vi.business.controller;

import com.vi.server.dto.ResponseDto;
import com.vi.server.exception.ValidateException;
import com.vi.server.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = ValidateException.class)
    @ResponseBody
    public ResponseDto validateExceptionHandler(ValidateException e) {
        try {
            log.warn(ExceptionUtil.getStackInfo(e));
        } catch (IOException ioException) {
            log.error("获取异常栈信息失败");
        }
        return ResponseDto.error().message("请求参数有误");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseDto validateExceptionHandler(RuntimeException e) {
        try {
            log.warn(ExceptionUtil.getStackInfo(e));
        } catch (IOException ioException) {
            log.error("获取异常栈信息失败");
        }
        return ResponseDto.error().message(e.getMessage());
    }
}
