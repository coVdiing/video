package com.vi.business.controller.admin;

import com.vi.server.dto.CategoryDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.CategoryService;
import com.vi.server.service.CourseCategoryService;
import com.vi.server.util.ValidateUtil;
import com.vi.server.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "商品分类 管理")
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    public static final String BUSINESS_NAME = "商品分类 ";

    @Resource
    private CategoryService categoryService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @ApiOperation("商品分类 列表")
    @PostMapping("/all")
    public ResponseDto all() {
        List<CategoryVo> all = categoryService.all();
        ResponseDto data = ResponseDto.ok().data(all);
        return data;
    }

    @ApiOperation("保存商品分类 ")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="商品分类 ",required = true)
            @RequestBody CategoryDto categoryDto) {
        // 保存校验
        ValidateUtil.isEmpty(categoryDto.getParent(),"父id");
        ValidateUtil.isEmpty(categoryDto.getName(),"名称");
        ValidateUtil.validateLength(categoryDto.getName(),"名称",1,50);
        categoryService.save(categoryDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除商品分类 ")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="商品分类 id",required = true)
            @PathVariable String id) {
        categoryService.delete(id);
        return ResponseDto.ok();
    }
}
