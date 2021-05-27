package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.User;
import com.vi.server.domain.UserExample;
import com.vi.server.dto.UserDto;
import com.vi.server.dto.PageDto;
import com.vi.server.enums.BusinessExceptionEnum;
import com.vi.server.exception.BusinessException;
import com.vi.server.mapper.UserMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        UserExample userExample = new UserExample();
        // criteria等同于where条件
        List<User> userList = userMapper.selectByExample(userExample);
        List<UserDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(userList);
        list = CopyUtil.copyList(userList, UserDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        log.info("user:{}",userDto);
        if (StringUtils.isEmpty(userDto.getId())) {
            insert(user);
        } else {
            update(user);
        }
    }

    private void insert(User user) {
        // 保存之前先查询用户名是否已经存在，如果存在不允许同名用户
        User byName = findByLoginName(user.getLoginName());
        if (byName != null) {
            log.error("业务异常,用户名已存在");
            throw new BusinessException(BusinessExceptionEnum.USER_NAME_REPEAT);
        }
        user.setId(UuidUtil.getShortUuid());
        userMapper.insert(user);
    }

    private void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User findByLoginName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andLoginNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
