package com.vi.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Slf4j
public class GatewayApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(GatewayApplication.class, args).getEnvironment();
        log.info("gateway启动成功!");
        log.info("gateway地址:http://127.0.0.1:{}",environment.getProperty("server.port"));
    }

}
