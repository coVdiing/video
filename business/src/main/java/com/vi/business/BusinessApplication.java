package com.vi.business;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.vi")
@MapperScan(basePackages = "com.vi.server.mapper")
public class BusinessApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        Environment environment = SpringApplication.run(BusinessApplication.class).getEnvironment();
        LOGGER.info("business启动成功!");
        LOGGER.info("business地址:http://localhost:127.0.0.1:{}", environment.getProperty("server.port"));
    }
}
