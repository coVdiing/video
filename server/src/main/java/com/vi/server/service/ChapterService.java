package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Chapter;
import com.vi.server.domain.ChapterExample;
import com.vi.server.dto.ChapterDto;
import com.vi.server.dto.ChapterPageDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.ChapterMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public void list(ChapterPageDto chapterPageDto) {
        PageHelper.startPage(chapterPageDto.getPage(), chapterPageDto.getPageSize());
        ChapterExample chapterExample = new ChapterExample();
        // criteria等同于where条件
        chapterExample.setOrderByClause("sort asc, gmt_create desc");
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        if (!StringUtils.isEmpty(chapterPageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(chapterList);
        list = CopyUtil.copyList(chapterList, ChapterDto.class);
        chapterPageDto.setTotal(pageInfo.getTotal());
        chapterPageDto.setList(list);
    }

    public void save(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        log.info("chapter:{}",chapterDto);
        if (StringUtils.isEmpty(chapterDto.getId())) {
            insert(chapter);
        } else {
            update(chapter);
        }
    }

    private void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());
        chapter.setGmtCreate(new Date());
        chapterMapper.insert(chapter);
    }

    private void update(Chapter chapter) {
        chapter.setGmtModified(new Date());
        chapterMapper.updateByPrimaryKeySelective(chapter);
    }

    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }
}
