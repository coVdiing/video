package com.vi.server.mapper.custom;

import com.vi.server.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomResourceMapper {
    void saveBatch(@Param("resourceList") List<ResourceDto> resourceList);
}
