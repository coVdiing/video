package com.vi.server.service.impl;

import com.vi.server.domain.Chapter;
import com.vi.server.domain.ChapterExample;
import com.vi.server.mapper.ChapterMapper;
import com.vi.server.service.ChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public List<Chapter> list() {
        ChapterExample chapterExample = new ChapterExample();
        // criteria等同于where条件
        chapterExample.setOrderByClause("id desc");
        return chapterMapper.selectByExample(chapterExample);
    }
}
