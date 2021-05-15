package com.vi.business.controller.admin;

import com.vi.server.dto.ResponseDto;
import com.vi.server.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: vi
 * @Date: 2021-05-15 18:08
 * @Version: 1.0
 * @Description:
 */
@Api(tags = "课程分类")
@RestController
@RequestMapping("/admin/course-category")
@Slf4j
public class CourseCategoryController {
    @Resource
    private CourseCategoryService courseCategoryService;

    @ApiOperation("被选中的课程对应分类id")
    @PostMapping("/get-selected-categories/{id}")
    public ResponseDto getSelectedCategories(
            @ApiParam("课程id")
            @PathVariable String id) {
        List<String> selectedCategories = courseCategoryService.getSelectedCategories(id);
        return ResponseDto.ok().data(selectedCategories);
    }
}
