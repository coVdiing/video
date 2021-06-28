package com.vi.system.controller.admin;

import com.vi.server.dto.RoleDto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.RoleService;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "管理")
@RestController
@RequestMapping("/admin/role")
@Slf4j
public class RoleController {
    public static final String BUSINESS_NAME = "";

    @Resource
    private RoleService roleService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        roleService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="",required = true)
            @RequestBody RoleDto roleDto) {
        // 保存校验
        ValidateUtil.isEmpty(roleDto.getId(),"id");
        ValidateUtil.isEmpty(roleDto.getName(),"角色");
        ValidateUtil.validateLength(roleDto.getName(),"角色",1,50);
        ValidateUtil.isEmpty(roleDto.getDesc(),"描述");
        ValidateUtil.validateLength(roleDto.getDesc(),"描述",1,100);
        roleService.save(roleDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="id",required = true)
            @PathVariable String id) {
        roleService.delete(id);
        return ResponseDto.ok();
    }

}
