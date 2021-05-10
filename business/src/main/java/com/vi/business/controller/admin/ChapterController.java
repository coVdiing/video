package com.vi.business.controller.admin;

import com.vi.server.dto.ChapterDto;
import com.vi.server.dto.ChapterPageDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.ChapterService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "大章管理")
@RestController
@RequestMapping("/admin/chapter")
@Slf4j
public class ChapterController {
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    @ApiOperation("大章列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody ChapterPageDto chapterPageDto) {
        ValidateUtil.isEmpty(chapterPageDto.getCourseId(),"课程名");
        chapterService.list(chapterPageDto);
        ResponseDto data = ResponseDto.ok().data(chapterPageDto);
        return data;
    }

    @ApiOperation("保存章节")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="章节",required = true)
            @RequestBody ChapterDto chapterDto) {
        ValidateUtil.isEmpty(chapterDto.getName(),"章节名");
        ValidateUtil.validateLength(chapterDto.getName(),"章节名",2,18);
        ValidateUtil.isEmpty(chapterDto.getCourseId(),"课程id");
        ValidateUtil.validateLength(chapterDto.getName(),"课程id",2,18);
        chapterService.save(chapterDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="章节",required = true)
            @PathVariable String id) {
        chapterService.delete(id);
        return ResponseDto.ok();
    }

}
