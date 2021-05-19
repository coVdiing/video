package com.vi.business.controller.admin;

import com.vi.server.dto.TeacherDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.TeacherService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "管理")
@RestController
@RequestMapping("/admin/teacher")
@Slf4j
public class TeacherController {
    public static final String BUSINESS_NAME = "";

    @Resource
    private TeacherService teacherService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        teacherService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="",required = true)
            @RequestBody TeacherDto teacherDto) {
        // 保存校验
        ValidateUtil.isEmpty(teacherDto.getName(),"姓名");
        ValidateUtil.validateLength(teacherDto.getName(),"姓名",1,50);
        ValidateUtil.validateLength(teacherDto.getNickname(),"昵称",1,50);
        ValidateUtil.validateLength(teacherDto.getPosition(),"职位",1,50);
        ValidateUtil.validateLength(teacherDto.getMotto(),"座右铭",1,50);
        ValidateUtil.validateLength(teacherDto.getIntro(),"简介",1,500);
        teacherService.save(teacherDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="id",required = true)
            @PathVariable String id) {
        teacherService.delete(id);
        return ResponseDto.ok();
    }

}
