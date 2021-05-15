package com.vi.server.mapper.custom;

import org.apache.ibatis.annotations.Param;

public interface CustomCourseMapper {
    void updateTime(@Param("courseId") String courseId);
}
