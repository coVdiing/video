package com.vi.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Author: vi
 * @Date: 2021-06-05 13:32
 * @Version: 1.0
 * @Description:
 */
@Component
@Slf4j
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // 如果请求地址不包含/admin/,不是控制台请求，不用拦截
        if (!path.contains("/admin/")) {
            return chain.filter(exchange);
        }
        if (path.contains("/system/admin/user/login")
                || path.contains("/system/admin/user/logout")) {
            log.info("不需要控制台登录验证:{}",path);
            return chain.filter(exchange);
        }
        // 获取header的loginToken参数
        String token = exchange.getRequest().getHeaders().getFirst("loginToken");
        log.info("登录验证开始,loginToken:{}:",token);
        if (token == null || token.isEmpty()) {
            log.info("loginToken无效,请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String object = stringRedisTemplate.opsForValue().get(token);
        if (object == null) {
            log.info("loginToken无效，请求拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
            log.info("已登录:{}",object);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
