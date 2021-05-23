package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Teacher;
import com.vi.server.domain.TeacherExample;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.TeacherDto;
import com.vi.server.mapper.TeacherMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CustomFileService fileService;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        TeacherExample teacherExample = new TeacherExample();
        // criteria等同于where条件
        teacherExample.setOrderByClause("gmt_create desc");
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        List<TeacherDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(teacherList);
        list = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        log.info("teacher:{}",teacherDto);
        if (StringUtils.isEmpty(teacherDto.getId())) {
            insert(teacher);
        } else {
            update(teacher);
        }
    }

    private void insert(Teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        teacher.setGmtCreate(new Date());
        teacherMapper.insert(teacher);
    }

    private void update(Teacher teacher) {
        teacher.setGmtModified(new Date());
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public void delete(String id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        teacherMapper.deleteByPrimaryKey(id);
        fileService.deleteImage(teacher.getImage());
    }
}
