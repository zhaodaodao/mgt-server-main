package com.example.mgtserver.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Hexrt
 * @description 文件上传工具
 * @create 2022-01-17 23:47
 **/
public class MultipartFileUtil {
    /**
     * 文件上传
     *
     * @param file         源文件
     * @param fileLocation 文件存储路径
     * @return 新文件名
     */
    public static String upload(MultipartFile file, String fileLocation) throws IOException {
        if (null == file || null == fileLocation) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = StringUtil.getUuidKey() + suffix;
        String path = fileLocation;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //保存文件
        String fullFilename = path + File.separator + filename;
        file.transferTo(new File(fullFilename));
        return filename;
    }

}
