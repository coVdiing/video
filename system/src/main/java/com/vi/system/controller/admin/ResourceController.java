package com.vi.system.controller.admin;

import com.vi.server.dto.ResourceDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.ResourceService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "资源管理")
@RestController
@RequestMapping("/admin/resource")
@Slf4j
public class ResourceController {
    public static final String BUSINESS_NAME = "资源";

    @Resource
    private ResourceService resourceService;

    @ApiOperation("资源列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        resourceService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存资源")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="资源",required = true)
            @RequestBody ResourceDto resourceDto) {
        // 保存校验
        ValidateUtil.isEmpty(resourceDto.getId(),"id");
        ValidateUtil.isEmpty(resourceDto.getName(),"名称");
        ValidateUtil.validateLength(resourceDto.getName(),"名称",1,100);
        ValidateUtil.validateLength(resourceDto.getPage(),"页面",1,50);
        ValidateUtil.validateLength(resourceDto.getRequest(),"请求",1,200);
        resourceService.save(resourceDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除资源")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="资源id",required = true)
            @PathVariable String id) {
        resourceService.delete(id);
        return ResponseDto.ok();
    }

}
