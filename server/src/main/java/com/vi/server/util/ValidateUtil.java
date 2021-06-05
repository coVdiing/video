package com.vi.server.util;

import com.vi.server.exception.ValidateException;
import org.springframework.util.StringUtils;

/**
 * 校验工具类
 */
public class ValidateUtil {
    /**
     * 空校验
     * @param arg
     * @param fieldName
     */
    public static void isEmpty(Object arg,String fieldName) {
        if (StringUtils.isEmpty(arg)) {
            throw new ValidateException(fieldName+"不能为空");
        }
    }

    /**
     * 长度校验
     * @param args
     * @param min
     * @param max
     */
    public static void validateLength(String args,String filedName,int min,int max) {
        if (StringUtils.isEmpty(args)) {
            throw new ValidateException(filedName + "不能为空");
        }
        if (args.length() < min || args.length() > max) {
            throw new ValidateException(filedName+"长度:"+min+"-"+max);
        }
    }

//    public static void main(String[] args) {
//        String arg = "12";
////        isEmpty(arg,"用户名");
//        validateLength(arg,"用户名",3,12);
//    }
}
