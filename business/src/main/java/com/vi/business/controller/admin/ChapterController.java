package com.vi.business.controller.admin;

import com.vi.server.dto.ChapterDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "大章管理")
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ApiOperation("大章列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
         chapterService.list(pageDto);
         return ResponseDto.ok().data(pageDto);
    }

    @ApiOperation("保存章节")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="章节",required = true)
            @RequestBody ChapterDto chapterDto) {
        chapterService.save(chapterDto);
        return ResponseDto.ok();
    }

}
