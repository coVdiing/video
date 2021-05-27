package com.vi.system.controller;

import com.vi.server.dto.ResponseDto;
import com.vi.server.exception.BusinessException;
import com.vi.server.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Author: vi
 * @Date: 2021-05-26 21:09
 * @Version: 1.0
 * @Description: system异常统一处理
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(BusinessException e) {
        try {
            log.warn(ExceptionUtil.getStackInfo(e));
        } catch (IOException ioException) {
            log.warn("异常信息输出失败");
        }
        return ResponseDto.error().message(e.getDesc());
    }
}
