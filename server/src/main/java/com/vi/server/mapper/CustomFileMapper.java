package com.vi.server.mapper;

import com.vi.server.domain.CustomFile;
import com.vi.server.domain.CustomFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomFileMapper {
    long countByExample(CustomFileExample example);

    int deleteByExample(CustomFileExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomFile record);

    int insertSelective(CustomFile record);

    List<CustomFile> selectByExample(CustomFileExample example);

    CustomFile selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomFile record, @Param("example") CustomFileExample example);

    int updateByExample(@Param("record") CustomFile record, @Param("example") CustomFileExample example);

    int updateByPrimaryKeySelective(CustomFile record);

    int updateByPrimaryKey(CustomFile record);
}