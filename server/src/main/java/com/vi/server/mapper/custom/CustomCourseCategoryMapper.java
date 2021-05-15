package com.vi.server.mapper.custom;

import com.vi.server.dto.CourseCategoryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomCourseCategoryMapper {
    void saveBatch(@Param("courseCategories") List<CourseCategoryDto> courseCategoryDtos);
}
