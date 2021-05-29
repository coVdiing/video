package com.vi.server.enums;

public enum BusinessExceptionEnum {
    USER_NAME_REPEAT("100", "用户名重复异常"),USER_INFO_NULL("101","用户信息为空");

    private String code;
    private String desc;

    BusinessExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
