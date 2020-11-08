package com.vi.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

    public static void main(String[] args) {
        Environment environment = SpringApplication.run(EurekaApplication.class, args).getEnvironment();
        LOG.info("启动成功!!");
        LOG.info("eureka地址:http://127.0.0.1:"+environment.getProperty("server.port"));
    }

}
