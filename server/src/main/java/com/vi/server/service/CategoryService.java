package com.vi.server.service;

import com.vi.server.domain.Category;
import com.vi.server.domain.CategoryExample;
import com.vi.server.dto.CategoryDto;
import com.vi.server.mapper.CategoryMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import com.vi.server.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public List<CategoryVo> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return dto2Vo(categories);
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
        Category category = categoryMapper.selectByPrimaryKey(id);
        // 如果删除一级分类，需要把关联的二级分类都删除
        if ("00000000".equals(category.getParent())) {
            CategoryExample example = new CategoryExample();
            example.createCriteria().andParentEqualTo(id);
            categoryMapper.deleteByExample(example);
        }
        categoryMapper.deleteByPrimaryKey(id);
    }

    private List<CategoryVo> dto2Vo(List<Category> categories) {
        List<Category> collect = categories.stream().filter(ele -> ("00000000".equals(ele.getParent()))).collect(Collectors.toList());
        List<CategoryVo> result = CopyUtil.copyList(collect, CategoryVo.class);
        HashMap<String, ArrayList<CategoryVo>> map = new HashMap<>();
        // 遍历result，用id作为key存入hashMap
        result.stream().forEach(ele -> map.put(ele.getId(),new ArrayList<CategoryVo>()));
        // 遍历categories
        categories.stream().forEach(ele -> {
            ArrayList<CategoryVo> value = map.get(ele.getParent());
            if ( value != null) {
                value.add(CopyUtil.copy(ele,CategoryVo.class));
            }
        });
        result.stream().forEach( ele -> {
            ArrayList<CategoryVo> categoryVos = map.get(ele.getId());
            ele.setChildren(categoryVos);
        });
        return result;
    }
}
