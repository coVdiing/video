package com.vi.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.server.domain.CustomFile;
import com.vi.server.domain.CustomFileExample;
import com.vi.server.dto.CustomFileDto;
import com.vi.server.dto.PageDto;
import com.vi.server.mapper.CustomFileMapper;
import com.vi.server.util.CopyUtil;
import com.vi.server.util.DateUtil;
import com.vi.server.util.ExceptionUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: vi
 * @Date: 2021-05-19 22:13
 * @Version: 1.0
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class CustomFileService {
    @Resource
    private CustomFileMapper customFileMapper;

    public String uploadImage(MultipartFile file) {
        if (file == null) {
            log.warn("头像为空");
            throw new RuntimeException("上传头像不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        // 校验文件格式
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index).toLowerCase();
        if (!".jpg".equals(fileType) && !".png".equals(fileType)) {
            log.warn("文件格式不支持");
            throw new RuntimeException("支持上传jpg,png格式图片");
        }
        try {
            String basePath = System.getProperty("user.dir") + "/" + "uploadDir"+"/"
                    + DateUtil.dateFormat(new Date(),DateUtil.PATTERN_ONLY_DATE);
            File base = new File(basePath);
            if (!base.exists()) {
                base.mkdir();
            }
            String fileName = DateUtil.dateFormat(new Date(), DateUtil.PATTERN_STRING);
            String suffix = UuidUtil.getShortUuid();
            String filePath = basePath + "/" + fileName + suffix + fileType;
            String smallFilePath = basePath + "/" + fileName + suffix +"_mini.jpg";
            File targetFile = new File(filePath);
            file.transferTo(targetFile);
            // 按照比例压缩图片
            Thumbnails.of(filePath).size(185, 185).keepAspectRatio(true).toFile(smallFilePath);
            // 删除原图片
            targetFile.delete();
            // 返回给前端的路径
            String resultPath = "uploadDir"+"/"
                    +DateUtil.dateFormat(new Date(),DateUtil.PATTERN_ONLY_DATE)+ "/" + fileName + suffix +"_mini.jpg";
            // 文件记录入表
            CustomFile record = new CustomFile();
            record.setName(originalFilename);
            record.setPath(resultPath);
            record.setSize((int) (new File(smallFilePath).length() / 1024));
            record.setSuffix(fileType);
            insert(record);
            // 返回文件id
            return record.getId();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteImage(String id) {
        if (id == null) {
            return;
        }
        delete(id);
    }

    /**
     * 根据文件id更新绑定关系
     * @param id
     */
    public void updateBindByPath(String id,String type) {
        CustomFileExample example = new CustomFileExample();
        CustomFile file = customFileMapper.selectByPrimaryKey(id);
        file.setBind("Y");
        file.setType(type);
        customFileMapper.updateByPrimaryKey(file);
    }

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        CustomFileExample customFileExample = new CustomFileExample();
        // criteria等同于where条件
        customFileExample.setOrderByClause("gmt_create desc");
        List<CustomFile> customFileList = customFileMapper.selectByExample(customFileExample);
        List<CustomFileDto> list = new ArrayList();
        PageInfo pageInfo = new PageInfo(customFileList);
        list = CopyUtil.copyList(customFileList, CustomFileDto.class);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(list);
    }

    public void save(CustomFileDto customFileDto) {
        CustomFile customFile = CopyUtil.copy(customFileDto, CustomFile.class);
        log.info("customFile:{}",customFileDto);
        if (StringUtils.isEmpty(customFileDto.getId())) {
            insert(customFile);
        } else {
            update(customFile);
        }
    }

    private void insert(CustomFile customFile) {
        customFile.setBind("N");
        customFile.setId(UuidUtil.getShortUuid());
        customFile.setGmtCreate(new Date());
        customFileMapper.insert(customFile);
    }

    private void update(CustomFile customFile) {
        customFile.setGmtModified(new Date());
        customFileMapper.updateByPrimaryKeySelective(customFile);
    }

    public void delete(String id) {
        String basePath = System.getProperty("user.dir");
        File file = new File(basePath + "/" + customFileMapper.selectByPrimaryKey(id).getPath());
        try {
            file.delete();
        } catch (Exception e) {
            try {
                log.error(ExceptionUtil.getStackInfo(e));
            } catch (IOException ioException) {
                log.error("删除图片时异常，获取异常信息栈失败,{}",ioException.getMessage());
            }
        }
        customFileMapper.deleteByPrimaryKey(id);
    }

    public List<CustomFileDto> findByIds(List<String> ids) {
        CustomFileExample example = new CustomFileExample();
        example.createCriteria().andIdIn(ids);
        List<CustomFile> customFiles = customFileMapper.selectByExample(example);
        return CopyUtil.copyList(customFiles,CustomFileDto.class);
    }
}
