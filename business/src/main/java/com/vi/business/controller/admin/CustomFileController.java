package com.vi.business.controller.admin;

import com.vi.server.dto.CustomFileDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.CustomFileService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "文件管理")
@RestController
@RequestMapping("/admin/custom-file")
@Slf4j
public class CustomFileController {
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private CustomFileService customFileService;

    @ApiOperation("上传图片")
    @PostMapping("/upload-image")
    public ResponseDto<String> upload(@RequestParam("file") MultipartFile file) {
        String path = customFileService.uploadImage(file);
        return ResponseDto.ok().data(path);
    }

    @ApiOperation("文件列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        customFileService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

}
