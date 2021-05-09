package com.vi.server.enums;

public enum ChargeEnum {
    CHARGE("C","收费"),
    FREE("F", "免费");

    private String code;
    private String desc;

    ChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
