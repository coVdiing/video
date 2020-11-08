package com.vi.system.controller;

import com.vi.system.domain.Test;
import com.vi.system.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return "早安打工人!";
    }

    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }
}
