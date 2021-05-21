package com.vi.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @Author: vi
 * @Date: 2021-05-20 23:50
 * @Version: 1.0
 * @Description: 文件上传映射配置
 */
@Configuration
@Slf4j
public class FileUploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadDir/**").
                addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "uploadDir" + File.separator);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String path = System.getProperty("user.dir");
        String location = path + "/uploadDir";
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
