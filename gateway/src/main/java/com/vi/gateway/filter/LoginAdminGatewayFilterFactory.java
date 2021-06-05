package com.vi.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: vi
 * @Date: 2021-06-05 13:30
 * @Version: 1.0
 * @Description:
 */
@Component
public class LoginAdminGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Resource
    LoginAdminGatewayFilter loginAdminGatewayFilter;

    @Override
    public GatewayFilter apply(Object config) {
        return loginAdminGatewayFilter;
    }
}
