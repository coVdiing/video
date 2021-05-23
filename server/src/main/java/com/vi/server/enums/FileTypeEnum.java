package com.vi.server.enums;

/**
 * 文件用途：C 课程，T 讲师
 */
public enum FileTypeEnum {
    COURSE("C","课程"), TEACHER("T", "讲师");

    private String code;
    private String desc;

    FileTypeEnum(String code, String desc) {
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
