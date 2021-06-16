package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Resource;
import com.vi.server.domain.ResourceExample;
import com.vi.server.dto.ResourceDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.ResourceMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        List<ResourceDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(resourceList);
        list = CopyUtil.copyList(resourceList, ResourceDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(ResourceDto resourceDto) {
        Resource resource = CopyUtil.copy(resourceDto, Resource.class);
        log.info("resource:{}",resourceDto);
        if (StringUtils.isEmpty(resourceDto.getId())) {
            insert(resource);
        } else {
            update(resource);
        }
    }

    private void insert(Resource resource) {
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    private void update(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
    }

    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);
    }
}
