package com.example.mgtserver.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hexrt
 * @description Zip压缩包工具 base on Zip4j
 * @create 2022-01-18 14:13
 * @reference https://github.com/srikanth-lingala/zip4j
 * <p>
 * 注意事项<br>
 * ZipFile使用isValidZipFile或isEncrypted会出现失效问题<br>
 **/
public class ZipUtil {
    /**
     * 默认解码字符编码
     */
    private static final String DEFAULT_CHARSET = "GBK";
    /**
     * 用户配置字符编码
     */
    private static String CONFIG_CHARSET;

    /**
     * 返回当前项目配置编码
     *
     * @return 项目编码
     */
    public static String charset() {
        return null == CONFIG_CHARSET
                ? DEFAULT_CHARSET
                : CONFIG_CHARSET;
    }

    /**
     * 用户配置项目编码
     *
     * @param configCharset 配置项目编码
     * @return 旧的编码
     */
    public static String configCharset(String configCharset) {
        String oldCharset = charset();
        CONFIG_CHARSET = configCharset.intern();
        return oldCharset;
    }

    /**
     * 验证zip是否正确，且无密码保护
     *
     * @param zipFile zip文件路径
     * @return 结果
     */
    public static boolean valid(ZipFile zipFile) {
        try {
            // 确保正确的zip文件，且没有密码保护
            if (zipFile.isValidZipFile()) {
                if (!zipFile.isEncrypted()) {
                    return true;
                } else {
                    System.out.println("有密码保护");
                    return false;
                }
            } else {
                System.out.println("非法zip文件");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解压预处理
     *
     * @return 将要解压在的文件名称
     */
    private static String preExtract(ZipFile zipFile, String targetPath, String charset) {

        // 是否合法
        if (!valid(new ZipFile(zipFile.getFile()))) {
            return null;
        }

        // 设置编码方式
        zipFile.setCharset(Charset.forName(charset));

        // 生成随机文件夹名称
        String directoryName = StringUtil.getUuidKey();
        String targetLocation = targetPath + File.separator + directoryName;

        // 建立文件夹
        if (!FileUtil.mkdirs(targetLocation)) {
            System.out.println("建立文件夹失败");
            return null;
        }

        return directoryName;
    }

    /**
     * 解压压缩包
     *
     * @param zipPath      压缩包
     * @param fileLocation 文件目录
     * @return 解压后的目录
     */
    public static String extractAll(String zipPath, String fileLocation) {
        return extractAll(zipPath, fileLocation, charset());
    }

    /**
     * 解压压缩包
     *
     * @param zipPath      压缩包
     * @param fileLocation 文件目录
     * @param charset      指定编码
     * @return 解压后的目录
     */
    public static String extractAll(String zipPath, String fileLocation, String charset) {
        ZipFile zipFile = new ZipFile(zipPath);
        String directoryName = preExtract(zipFile, fileLocation, charset);
        if (null == directoryName) {
            System.out.println("预处理失败");
            return null;
        }
        String filePath = fileLocation + File.separator + directoryName;


        // 解压
        try {
            zipFile.extractAll(filePath);
            System.out.println("解压成功");
        } catch (ZipException e) {
            System.out.println("解压失败");
            e.printStackTrace();
            return null;
        }

        return directoryName;
    }

    /**
     * 将第一个被匹配的文件的当前目录作为一级目录，同级以及往后解压
     *
     * @param zipLocation 压缩包
     * @param targetPath  文件目录
     * @param pattern     正则匹配值
     * @return 解压后的目录地址
     */
    public static String extractAllBaseTarget(String zipLocation, String targetPath, String pattern) throws ZipException {
        return extractAllBaseTarget(zipLocation, targetPath, pattern, charset());
    }

    /**
     * 将第一个被匹配的文件的当前目录作为一级目录，同级以及往后解压
     *
     * @param zipLocation 压缩包
     * @param targetPath  文件目录
     * @param pattern     正则匹配值
     * @param charset     指定的编码
     * @return 解压后的目录地址
     */
    public static String extractAllBaseTarget(String zipLocation, String targetPath, String pattern, String charset) throws ZipException {
        // 验证合法并且预处理创建目录
        ZipFile zipFile = new ZipFile(zipLocation);
        String directoryName = preExtract(zipFile, targetPath, charset);
        if (null == directoryName) {
            System.out.println("预处理失败");
            return null;
        }
        String targetLocation = targetPath + File.separator + directoryName;


        List<FileHeader> fileHeaders = null;
        try {
            fileHeaders = zipFile.getFileHeaders();
        } catch (ZipException e) {
            System.out.println("获取压缩包内文件列表失败");
            e.printStackTrace();
            return null;
        }

        // 获取FileHeader，寻找第一个匹配的文件
        ArrayList<ZipFileHeader> list = new ArrayList<>();
        for (FileHeader fileHeader : fileHeaders) {
            list.add(new ZipFileHeader(fileHeader.getFileName()));
        }

        // 根据关键字排序
        Collections.sort(list);

        // 目标文件前缀
        String prefix = "";
        String target = null;
        // 目标的深度
        int baseDeep = 0;

        // 找到前缀
        for (ZipFileHeader zipFileHeader : list) {
            if (zipFileHeader.getFileName().endsWith(pattern)) {
                int index = zipFileHeader.getFileName().lastIndexOf(ZipFileHeader.separatorChar);
                baseDeep = zipFileHeader.getDeep();
                if (index != -1) {
                    prefix = zipFileHeader.getFileName().substring(0, index + 1);
                }
                target = zipFileHeader.getFileName().substring(index + 1);
                break;
            }
        }

        // 未找到匹配文件(2022年4月5日 00点20分 修复)
        if (null == target) {
            return null;
        }

        // 解压列表
        List<ZipExtractHeader> extractHeaders = new ArrayList<>();

        for (ZipFileHeader zipFileHeader : list) {
            // 同一目录，子树目录才解压
            if (zipFileHeader.getFileName().startsWith(prefix) && zipFileHeader.getDeep() >= baseDeep) {
                String fileName = zipFileHeader.getFileName();
                String targetName = fileName.substring(prefix.length());
                // 目录情况特判
                if (targetName.endsWith(ZipFileHeader.separator)) {
                    targetName = targetName.substring(0, targetName.length() - 1) + File.separatorChar;
                }
                // 正常解压，重命名
                extractHeaders.add(new ZipExtractHeader(fileName, targetName));
            }
        }

        // 尝试解压
        for (ZipExtractHeader zip : extractHeaders) {
            try {
                zipFile.extractFile(zip.getFileName(), targetLocation, zip.getTargetName());
            } catch (Exception e) {
                //todo 回滚操作，删除解压的所有文件
                throw e;
            }
        }

        return directoryName + File.separator + target;
    }

}

/**
 * 工具实体
 */
class ZipFileHeader implements Comparable<ZipFileHeader> {
    /**
     * 分隔符
     */
    public static char separatorChar = '/';
    public static String separator = separatorChar + "";

    /**
     * 文件名字
     */
    private String fileName;
    /**
     * 文件目录深度，用作排序需要
     */
    private int deep = 0;

    public ZipFileHeader(String fileName) {
        this.fileName = fileName;
        this.deep = 0;

        // 计算文件深度
        for (int i = 0; i < fileName.length(); ++i) {
            if (fileName.charAt(i) == separatorChar) {
                ++this.deep;
            }
        }

        //目录降级
        if (fileName.endsWith(separator)) {
            --this.deep;
        }

    }


    @Override
    public int compareTo(ZipFileHeader o) {
        if (this.deep == o.deep) {
            return this.fileName.compareTo(o.fileName);
        } else {
            return this.deep < o.deep ? -1 : 1;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }
}

/**
 * 解压的文件头信息
 */
class ZipExtractHeader {
    private String fileName;
    private String targetName;

    /**
     * 解压文件
     *
     * @param fileName   解压文件名
     * @param targetName 目的输出名
     */
    public ZipExtractHeader(String fileName, String targetName) {
        this.fileName = fileName;
        this.targetName = targetName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}