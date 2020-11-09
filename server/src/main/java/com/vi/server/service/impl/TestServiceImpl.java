package com.vi.server.service.impl;

import com.vi.system.domain.Test;
import com.vi.system.mapper.TestMapper;
import com.vi.system.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> list() {
        return testMapper.list();
    }
}
