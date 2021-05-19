package com.vi.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: vi
 * @Date: 2021-05-19 23:27
 * @Version: 1.0
 * @Description: 日期工具类
 */
public class DateUtil {
    public static final String PATTERN_STRING = "YYYYMMddHHmmss";
    public static final String PATTERN_STRING_WITH_SEPERATOR = "YYYY-MM-dd HH:mm:ss";

    public static String dateFormat(Date date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
