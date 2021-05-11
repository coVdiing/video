package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Category;
import com.vi.server.domain.CategoryExample;
import com.vi.server.dto.CategoryDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.CategoryMapper;
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
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        CategoryExample categoryExample = new CategoryExample();
        // criteria等同于where条件
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(categoryList);
        list = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        log.info("category:{}",categoryDto);
        if (StringUtils.isEmpty(categoryDto.getId())) {
            insert(category);
        } else {
            update(category);
        }
    }

    private void insert(Category category) {
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    private void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void delete(String id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
