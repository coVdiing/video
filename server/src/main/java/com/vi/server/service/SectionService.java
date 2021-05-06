package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Section;
import com.vi.server.domain.SectionExample;
import com.vi.server.dto.SectionDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.SectionMapper;
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
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        SectionExample sectionExample = new SectionExample();
        // criteria等同于where条件
        sectionExample.setOrderByClause("gmt_create desc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        List<SectionDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(sectionList);
        list = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        log.info("section:{}",sectionDto);
        if (StringUtils.isEmpty(sectionDto.getId())) {
            insert(section);
        } else {
            update(section);
        }
    }

    private void insert(Section section) {
        section.setId(UuidUtil.getShortUuid());
        section.setGmtCreate(new Date());
        sectionMapper.insert(section);
    }

    private void update(Section section) {
        section.setGmtModified(new Date());
        sectionMapper.updateByPrimaryKeySelective(section);
    }

    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
