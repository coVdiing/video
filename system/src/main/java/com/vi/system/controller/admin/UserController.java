package com.vi.system.controller.admin;

import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.dto.UserDto;
import com.vi.server.service.UserService;
import com.vi.server.util.ValidateUtil;
import com.vi.server.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "管理")
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserController {
    public static final String BUSINESS_NAME = "";

    @Resource
    private UserService userService;

    @ApiOperation("列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        userService.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="",required = true)
            @RequestBody UserDto userDto) {
        // 保存校验
        ValidateUtil.isEmpty(userDto.getLoginName(),"登录名");
        ValidateUtil.validateLength(userDto.getLoginName(),"登录名",1,50);
        ValidateUtil.isEmpty(userDto.getName(),"昵称");
        ValidateUtil.validateLength(userDto.getName(),"昵称",1,50);
        ValidateUtil.isEmpty(userDto.getPassword(),"密码");
        userService.save(userDto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="id",required = true)
            @PathVariable String id) {
        userService.delete(id);
        return ResponseDto.ok();
    }

    @ApiOperation("更新密码")
    @PostMapping("/save-password")
    public ResponseDto savePassword(
            @ApiParam(value="用户信息",required = true)
            @RequestBody UserDto user) {
        userService.savePassword(user);
        return ResponseDto.ok();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseDto<UserVo> login(
            @ApiParam(value="用户信息",required = true)
            @RequestBody UserDto user) {
        UserVo userVo = userService.login(user);
        return ResponseDto.ok().data(userVo);
    }
}
