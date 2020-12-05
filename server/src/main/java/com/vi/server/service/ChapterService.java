package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.vi.server.domain.Chapter;
import com.vi.server.domain.ChapterExample;
import com.vi.server.dto.ChapterDto;
import com.vi.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        PageHelper.startPage(1, 1);
        ChapterExample chapterExample = new ChapterExample();
        // criteria等同于where条件
        chapterExample.setOrderByClause("id desc");
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> list = new ArrayList();
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            list.add(chapterDto);
        }
        return list;
    }
}
