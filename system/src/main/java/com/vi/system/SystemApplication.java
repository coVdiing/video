package com.vi.system;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
public class SystemApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        Environment environment = SpringApplication.run(SystemApplication.class).getEnvironment();
        LOGGER.info("system启动成功!");
        LOGGER.info("system地址:http://localhost:127.0.0.1:{}", environment.getProperty("server.port"));
    }
}
