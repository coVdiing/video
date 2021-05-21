package com.vi.server.service;

import com.vi.server.util.DateUtil;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
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

    public String upload(MultipartFile file) {
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
                    +DateUtil.dateFormat(new Date(),DateUtil.PATTERN_ONLY_DATE);
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
            return resultPath;
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
