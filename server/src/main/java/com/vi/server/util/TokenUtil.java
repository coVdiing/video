package com.vi.server.util;

import com.vi.server.consts.SessionConst;

/**
 * @Author: vi
 * @Date: 2021-06-05 11:11
 * @Version: 1.0
 * @Description:Token工具类
 */
public class TokenUtil {

    // 登录验证token
    public static String getLoginToken() {
        return SessionConst.LOGIN_USER+"_"+UuidUtil.getShortUuid();
    }
}
