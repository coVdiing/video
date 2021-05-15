package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.CourseCategory;
import com.vi.server.domain.CourseCategoryExample;
import com.vi.server.dto.CategoryDto;
import com.vi.server.dto.CourseCategoryDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.CourseCategoryMapper;
import com.vi.server.mapper.custom.CustomCourseCategoryMapper;
import com.vi.server.util.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class CourseCategoryService {
    @Resource
    private CourseCategoryMapper courseCategoryMapper;
    @Resource
    private CustomCourseCategoryMapper customCourseCategoryMapper;
    @Resource
    private CategoryService categoryService;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
        // criteria等同于where条件
        courseCategoryExample.setOrderByClause("gmt_create desc");
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);
        List<CourseCategoryDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(courseCategoryList);
        list = CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void saveBatch(List<CourseCategoryDto> courseCategoryDtos) {
        customCourseCategoryMapper.saveBatch(courseCategoryDtos);
    }

    public void deleteByCourseId(String courseId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);
    }

    public List<String> getSelectedCategories(String coursId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(coursId);
        List<CourseCategory> courseCategories = courseCategoryMapper.selectByExample(example);
        List<String> ids = new LinkedList<>();
        courseCategories.stream().forEach(ele -> ids.add(ele.getCategoryId()));
        return ids;
    }
}
