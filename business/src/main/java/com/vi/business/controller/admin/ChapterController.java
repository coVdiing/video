package com.vi.business.controller.admin;

import com.vi.server.domain.Chapter;
import com.vi.server.dto.ChapterDto;
import com.vi.server.dto.PageDto;
import com.vi.server.result.ResultDTO;
import com.vi.server.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "大章管理")
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ApiOperation("大章列表")
    @PostMapping("/list")
    public PageDto list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
         chapterService.list(pageDto);
         return pageDto;
    }

    @ApiOperation("保存章节")
    @PostMapping("/save")
    public ResultDTO save(
            @ApiParam(value="章节",required = true)
            @RequestBody ChapterDto chapterDto) {
        chapterService.save(chapterDto);
        return ResultDTO.ok();
    }

}
