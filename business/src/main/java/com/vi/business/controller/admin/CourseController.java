package com.vi.business.controller.admin;

import com.vi.server.dto.CourseDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.CourseService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "管理")
@RestController
@RequestMapping("/admin/course")
@Slf4j
public class CourseController {
    public static final String BUSINESS_NAME = "";

    @Resource
    private CourseService courseService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        courseService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="",required = true)
            @RequestBody CourseDto courseDto) {
        // 保存校验
        ValidateUtil.isEmpty(courseDto.getName(),"名称");
        ValidateUtil.validateLength(courseDto.getName(),"名称",1,50);
        ValidateUtil.validateLength(courseDto.getSummary(),"概述",1,2000);
//        ValidateUtil.validateLength(courseDto.getImage(),"封面",1,100);
        courseService.save(courseDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="id",required = true)
            @PathVariable String id) {
        courseService.delete(id);
        return ResponseDto.ok();
    }

}
