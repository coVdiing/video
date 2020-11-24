package com.vi.system.controller;

import com.vi.server.domain.Chapter;
import com.vi.server.domain.Test;
import com.vi.server.service.ChapterService;
import com.vi.server.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @GetMapping("/list")
    public List<Chapter> list() {
        return chapterService.list();
    }
}
