package com.vi.business.controller.admin;

import com.vi.server.dto.${Domain}Dto;
import com.vi.server.dto.PageDto;
import com.vi.server.dto.ResponseDto;
import com.vi.server.service.${Domain}Service;
import com.vi.server.util.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "${tableNameCn}管理")
@RestController
@RequestMapping("/admin/${domain}")
@Slf4j
public class ${Domain}Controller {
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private ${Domain}Service ${domain}Service;

    @ApiOperation("${tableNameCn}列表")
    @PostMapping("/list")
    public ResponseDto<PageDto> list(
            @ApiParam(value="分页参数",required = true)
            @RequestBody PageDto pageDto) {
        ${domain}Service.list(pageDto);
        ResponseDto data = ResponseDto.ok().data(pageDto);
        return data;
    }

    @ApiOperation("保存${tableNameCn}")
    @PostMapping("/save")
    public ResponseDto save(
            @ApiParam(value="${tableNameCn}",required = true)
            @RequestBody ${Domain}Dto ${domain}Dto) {
        // 保存校验
        <#list fieldList as field>
            <#if !field.nullAble>
        ValidateUtil.isEmpty(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidateUtil.validateLength(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}",1,${field.length});
            </#if>
        </#list>
        ${domain}Service.save(${domain}Dto);
        return ResponseDto.ok();
    }

    @ApiOperation("删除${tableNameCn}")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(
            @ApiParam(value="${tableNameCn}id",required = true)
            @PathVariable String id) {
        ${domain}Service.delete(id);
        return ResponseDto.ok();
    }

}
