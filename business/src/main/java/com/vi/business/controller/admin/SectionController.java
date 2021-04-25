package com.vi.business.controller.admin;

import com.vi.server.dto.SectionDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.SectionService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "大章管理")
@RestController
@RequestMapping("/admin/section")
@Slf4j
public class SectionController {
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService sectionService;

    @ApiOperation("大章列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        sectionService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存章节")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="章节",required = true)
            @RequestBody SectionDto sectionDto) {
        sectionService.save(sectionDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="章节",required = true)
            @PathVariable String id) {
        sectionService.delete(id);
        return ResponseDto.ok();
    }

}
