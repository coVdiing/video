package com.vi.business.controller;

import com.vi.server.dto.ResponseDto;
import com.vi.server.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: vi
 * @Date: 2021-05-19 21:57
 * @Version: 1.0
 * @Description: 文件上传
 */
@Api(tags = "文件上传接口")
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseDto<String> upload(@RequestParam("file") MultipartFile file) {
        String path = fileService.upload(file);
        return ResponseDto.ok().data(path);
    }
}
