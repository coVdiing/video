package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Resource;
import com.vi.server.domain.ResourceExample;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResourceDto;
import com.vi.server.mapper.ResourceMapper;
import com.vi.server.mapper.custom.CustomResourceMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private CustomResourceMapper customResourceMapper;

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
        log.info("resource:{}", resourceDto);
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

    public List<ResourceDto> findAll() {
        ResourceExample example = new ResourceExample();
        example.setOrderByClause("id asc");
        List<Resource> resources = resourceMapper.selectByExample(example);
        return CopyUtil.copyList(resources, ResourceDto.class);
    }

    /**
     * 从资源表直接查出来的对象集合是没有关联关系的，填充对应的关联关系以后返回一个顶级根节点对象
     *
     * @param dtoList
     * @return
     */
    public List<ResourceDto> list2Root(List<ResourceDto> dtoList) {
        for (int i = dtoList.size() - 1; i > 0; i--) {
            ResourceDto current = dtoList.get(i);
            String parentId = current.getParent();
            if (!StringUtils.isEmpty(parentId)) {
                for (int j = i - 1; j >= 0; j--) {
                    if (parentId.equals(dtoList.get(j).getId())) {
                        List<ResourceDto> children = dtoList.get(j).getChildren();
                        children.add(current);
                    }
                }
            }
            // 把当前元素填充到父节点以后可以移除掉该元素了，注意要逆序删除，否则会报错
            dtoList.remove(i);
        }
        return dtoList;
    }

    /**
     * 保存资源树
     *
     * @param resourceDto
     */
    public void saveTree(ResourceDto resourceDto) {
        // 先删除表中现有数据
        ResourceExample example = new ResourceExample();
        int deleteResult = resourceMapper.deleteByExample(example);
        List<ResourceDto> dtoList = new ArrayList<>();
        root2List(resourceDto,dtoList);
        customResourceMapper.saveBatch(dtoList);
    }

    private void root2List(ResourceDto resourceDto,List<ResourceDto> dtoList) {
        if (resourceDto == null) {
            return;
        }
        dtoList.add(resourceDto);
        for (ResourceDto child : resourceDto.getChildren()) {
            root2List(child,dtoList);
        }
    }
}
