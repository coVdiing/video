package com.vi.server.util;

import java.io.*;

/**
 * @Author: vi
 * @Date: 2021-05-22 15:09
 * @Version: 1.0
 * @Description: 异常工具类
 */
public class ExceptionUtil {
    private static final String EXCEPTION_INFO_TEMP_FILENAME = "exception_temp.txt";
    /**
     * 将异常对象中的栈信息返回一个String对象，方便打印日志
     * @param e
     * @return
     */
    public static String getStackInfo(Exception e) throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream(EXCEPTION_INFO_TEMP_FILENAME));
        // 异常信息输出到文件中
        e.printStackTrace(ps);
        // 从文件中读取
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(EXCEPTION_INFO_TEMP_FILENAME)));
        char[] temp = new char[1024];
        StringBuilder log = new StringBuilder();
        int length = 0;
        while ((length = br.read(temp)) != -1) {
            if (length != 1024) {
                log.append(temp, 0, length - 1);
            } else {
                log.append(temp);
            }
        }
        return log.toString();
    }
}
