package com.vi.server.mapper.custom;

import com.vi.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

public interface CustomCourseMapper {
    void updateTime(@Param("courseId") String courseId);

    void updateSortForward(@Param("sort") SortDto sort);

    void updateSortRetreat(@Param("sort") SortDto sort);
}
