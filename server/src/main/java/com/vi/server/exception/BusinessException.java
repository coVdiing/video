package com.vi.server.exception;

import com.vi.server.enums.BusinessExceptionEnum;

/**
 * @Author: vi
 * @Date: 2021-05-26 20:52
 * @Version: 1.0
 * @Description:
 */
public class BusinessException extends RuntimeException {
    private String code;
    private String desc;

    public BusinessException(BusinessExceptionEnum businessExceptionEnum) {
        this.code = businessExceptionEnum.getCode();
        this.desc = businessExceptionEnum.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
