package com.vi.business.controller.admin;

import com.vi.server.domain.Chapter;
import com.vi.server.dto.ChapterDto;
import com.vi.server.dto.PageDto;
import com.vi.server.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto) {
         chapterService.list(pageDto);
         return pageDto;
    }
}
