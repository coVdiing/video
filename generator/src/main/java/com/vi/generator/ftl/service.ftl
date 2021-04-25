package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.${Domain};
import com.vi.server.domain.${Domain}Example;
import com.vi.server.dto.${Domain}Dto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.${Domain}Mapper;
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
public class ${Domain}Service {
    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        // criteria等同于where条件
        ${domain}Example.setOrderByClause("gmt_create desc");
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        List<${Domain}Dto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(${domain}List);
        list = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(${Domain}Dto ${domain}Dto) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        log.info("${domain}:{}",${domain}Dto);
        if (StringUtils.isEmpty(${domain}Dto.getId())) {
            insert(${domain});
        } else {
            update(${domain});
        }
    }

    private void insert(${Domain} ${domain}) {
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}.setGmtCreate(new Date());
        ${domain}Mapper.insert(${domain});
    }

    private void update(${Domain} ${domain}) {
        ${domain}.setGmtModified(new Date());
        ${domain}Mapper.updateByPrimaryKeySelective(${domain});
    }

    public void delete(String id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
