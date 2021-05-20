package com.vi.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
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
        log.info("文件上传映射");
        registry.addResourceHandler("/uploadDir/**").
                addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "uploadDir" + File.separator);
        log.info("file:" + System.getProperty("user.dir") + File.separator + "uploadDir" + File.separator);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        log.info("文件上传配置");
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // tmp.dir参数在启动脚本中设置
        String path = System.getProperty("user.dir");
        String location = path + "/uploadDir";
        // 明确指定上传文件的临时目录
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

}
