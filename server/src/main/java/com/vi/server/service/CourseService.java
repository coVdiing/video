package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Course;
import com.vi.server.domain.CourseCategory;
import com.vi.server.domain.CourseContent;
import com.vi.server.domain.CourseExample;
import com.vi.server.dto.*;
import com.vi.server.enums.ChargeEnum;
import com.vi.server.enums.CourseLevelEnum;
import com.vi.server.enums.CourseStatusEnum;
import com.vi.server.mapper.CourseContentMapper;
import com.vi.server.mapper.CourseMapper;
import com.vi.server.mapper.custom.CustomCourseMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CustomCourseMapper customCourseMapper;
    @Resource
    private CourseCategoryService courseCategoryService;
    @Resource
    private CourseContentMapper courseContentMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        CourseExample courseExample = new CourseExample();
        // criteria等同于where条件
        courseExample.setOrderByClause("gmt_create desc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        List<CourseDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(courseList);
        list = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        log.info("course:{}",courseDto);
        if (StringUtils.isEmpty(courseDto.getId())) {
            insert(course);
        } else {
            update(course);
            courseCategoryService.deleteByCourseId(course.getId());
        }
        List<CategoryDto> categoryDtos = courseDto.getCategoryDtos();
        List<CourseCategoryDto> courseCategoryDtos = new LinkedList<>();
        categoryDtos.stream().forEach(
                ele -> courseCategoryDtos.
                        add(new CourseCategoryDto(UuidUtil.getShortUuid(),
                                               course.getId(),
                                               ele.getId())));
        courseCategoryService.saveBatch(courseCategoryDtos);
    }

    private void insert(Course course) {
        course.setId(UuidUtil.getShortUuid());
        course.setGmtCreate(new Date());
        if (course.getStatus() == null) {
            course.setStatus(CourseStatusEnum.DRAFT.getCode());
        }
        if (course.getCharge() == null) {
            course.setCharge(ChargeEnum.CHARGE.getCode());
        }
        if (course.getLevel() == null) {
            course.setLevel(CourseLevelEnum.ONE.getCode());
        }
        courseMapper.insert(course);
    }

    private void update(Course course) {
        course.setGmtModified(new Date());
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
        courseCategoryService.deleteByCourseId(id);
    }

    public void updateTime(String courseId) {
        customCourseMapper.updateTime(courseId);
    }

    public void saveContent(CourseContentDto courseContentDto) {
        CourseContent courseContent = CopyUtil.copy(courseContentDto, CourseContent.class);
        courseContent.setGmtModified(new Date());
        int updateResult = courseContentMapper.updateByPrimaryKey(courseContent);
        if (updateResult <= 0) {
            courseContent.setGmtCreate(new Date());
            courseContentMapper.insert(courseContent);
        }
    }

    public CourseContent getContent(String id) {
        return courseContentMapper.selectByPrimaryKey(id);
    }
}
