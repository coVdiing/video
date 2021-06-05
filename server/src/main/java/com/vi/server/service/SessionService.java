package com.vi.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: vi
 * @Date: 2021-05-30 14:56
 * @Version: 1.0
 * @Description: session服务:使用redis实现
 */
@Service
@Slf4j
public class SessionService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value, long time, TimeUnit timeUnit) {
        log.info("保存:{}",key);
        stringRedisTemplate.opsForValue().set(key,value,time,timeUnit);
    }

    public void remove(String key) {
        log.info("移除loginToken");
        stringRedisTemplate.delete(key);
    }


}
