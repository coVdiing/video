package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.Role;
import com.vi.server.domain.RoleExample;
import com.vi.server.dto.RoleDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.RoleMapper;
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
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        RoleExample roleExample = new RoleExample();
        // criteria等同于where条件
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        List<RoleDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(roleList);
        list = CopyUtil.copyList(roleList, RoleDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(RoleDto roleDto) {
        Role role = CopyUtil.copy(roleDto, Role.class);
        log.info("role:{}",roleDto);
        if (StringUtils.isEmpty(roleDto.getId())) {
            insert(role);
        } else {
            update(role);
        }
    }

    private void insert(Role role) {
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    private void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
