package com.vi.business.controller.admin;

import com.vi.server.domain.Chapter;
import com.vi.server.dto.ChapterDto;
import com.vi.server.service.ChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @GetMapping("/list")
    public List<ChapterDto> list() {
        return chapterService.list();
    }
}
