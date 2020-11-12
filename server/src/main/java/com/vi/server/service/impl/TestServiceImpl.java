package com.vi.server.service.impl;

import com.vi.server.domain.Test;
import com.vi.server.domain.TestExample;
import com.vi.server.mapper.TestMapper;
import com.vi.server.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> list() {
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
