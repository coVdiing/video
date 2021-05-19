package com.vi.server.service;

import com.vi.server.util.DateUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: vi
 * @Date: 2021-05-19 22:13
 * @Version: 1.0
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class FileService {

    public void upload(MultipartFile file) {
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
            String basePath = System.getProperty("user.dir") + File.separator + "uploadDir";
            File base = new File(basePath);
            if (!base.exists()) {
                base.mkdir();
            }
            String fileName = DateUtil.dateFormat(new Date(), DateUtil.PATTERN_STRING);
            String suffix = UuidUtil.getShortUuid();
            String filePath = basePath + File.separator + fileName + suffix + fileType;
            File targetFile = new File(filePath);
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
