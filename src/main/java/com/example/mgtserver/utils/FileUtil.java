package com.example.mgtserver.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/***
 * 文件操作
 * @author Hexrt
 * @time 2020-04-22 19:49
 */
public class FileUtil {
    /**
     * 读取文件
     *
     * @param fileName 文件名
     * @return
     */
    public static String read(String fileName) {
        StringBuilder sbd = new StringBuilder();
        File file = new File(fileName);
        FileReader filer = null;
        BufferedReader reader = null;

        if (!file.exists()) {//判断文件是否存在
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                ///Do Sth.
                return null;
            }
        }
        try {
            filer = new FileReader(file);
            reader = new BufferedReader(filer);
            //读取文档
            String str = null;
            while ((str = reader.readLine()) != null) {
                sbd.append(str + "\n");
            }
            if (sbd.length() != 0) {
                sbd.deleteCharAt(sbd.length() - 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            //Do Sth.
        } finally {
            try {
                reader.close();
                filer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
                //Do Sth.
            }
        }
        return sbd.toString();
    }

    /**
     * 文件写入
     *
     * @param fileName 文件名
     * @param content  内容
     * @param type     false为覆盖 true为追加
     */
    public static boolean write(String fileName, String content, boolean type) {
        if (fileName.contains("/")) {
            String path = fileName.subSequence(0, fileName.lastIndexOf("/")).toString();
            if (!path.trim().isEmpty() && !mkdirs(path)) {
                return false;
            }
        }
        File file = new File(fileName);
        FileWriter filew = null;
        BufferedWriter writer = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //Do Sth.
                return false;
            }
        }
        ///
        try {
            //                  要保存的文件  写入类型
            filew = new FileWriter(file, type);
            writer = new BufferedWriter(filew);
            writer.write(content);
        } catch (IOException e) {
            //Do Sth.
            return false;
        } finally {
            try {
                writer.close();
                filew.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    /**
     * 流形式保存文件
     *
     * @param fileName
     * @param content
     * @param type
     * @return
     */
    public static boolean save(String fileName, InputStream content, boolean type) {
        //如果路径下不存在的话，就创建目录
        if (fileName.contains("/")) {
            String path = fileName.subSequence(0, fileName.lastIndexOf("/")).toString();
            if (!path.trim().isEmpty() && !mkdirs(path)) {
                return false;
            }
        }
        File file = new File(fileName);
        FileOutputStream outputStream = null;
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch(IOException e) {
//                //Do Sth.
//                return false;
//            }
//        }
        try {
            outputStream = new FileOutputStream(file);
            System.out.println("get OutputStream!");
            byte[] bys = new byte[8];
            int len = 0;
            while ((len = content.read(bys)) != -1) {
                System.out.println("Start read!");
                outputStream.write(bys, 0, len);
                System.out.println("write one!");
            }
            System.out.println("???");
        } catch (IOException e) {
            //Do Sth.
            System.out.println("read and write bad!");
            return false;
        } finally {
            try {
                outputStream.close();
                content.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 保存文件
     *
     * @param filename 文件名
     * @param bys      文件的子节码
     * @param type     保存方式
     * @return 结果
     */
    public static boolean save(String filename, byte[] bys, boolean type) {
        File file = new File(filename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bys);
        } catch (Exception e) {
            return false;
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 文件复制
     *
     * @param sourceFileName 源文件
     * @param targetFileName 目标文件
     * @param type           方式
     * @return 结果
     */
    public static boolean copy(String sourceFileName, String targetFileName, boolean type) {
        //如果路径下不存在的话，就创建目录
        if (targetFileName.contains("/")) {
            String path = targetFileName.subSequence(0, targetFileName.lastIndexOf("/")).toString();
            if (!path.trim().isEmpty() && !mkdirs(path)) {
                return false;
            }
        }
        //获取到两个文件对象
        File tarFile = new File(targetFileName);
        File sourceFile = new File(sourceFileName);
        if (!sourceFile.exists()) {
            return false;
        }
        FileOutputStream outputStream = null;
        if (!tarFile.exists()) {
            try {
                tarFile.createNewFile();
            } catch (IOException e) {
                //Do Sth.
                return false;
            }
        }
        //尝试进行输出
        try {
            InputStream inputStream = new FileInputStream(sourceFile);
            byte[] bys = new byte[8];
            int len = 0;
            while ((len = inputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, len);
            }
        } catch (IOException e) {
            //Do Sth.
            return false;
        } finally {
            //关闭文件流
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

    /**
     * 复制文件
     *
     * @param src 源文件路径
     * @param dst 目标文件路径
     */
    public static void copyFile(String src, String dst) {
        //1 提供读入 输出文件（字节流）（可以为txt 或者 图片 视频等）
        File file1 = new File(src);
        File file2 = new File(dst);
        //提供流对象
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            // 实现文件复制
            byte[] b = new byte[10];//读取字节长度，根据所传文件大小来定，图片视频一般为1024及其倍数
            int len;//每次读入的长度
            while ((len = fis.read(b)) != -1) {
                //写入文件
                fos.write(b, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //先关输出
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //再关输入
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 是否存在文件
     *
     * @param fileName 文件名
     * @return
     */
    public static boolean exist(String fileName) {
        File file = new File(fileName);
        return (file.exists());
    }

    /**
     * 创建单个文件夹
     *
     * @param dirName
     * @return
     */
    public static boolean mkdir(String dirName) {
        //获取到目录文件
        File file = new File(dirName);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 创建多个层级文件夹
     *
     * @param dirsName
     * @return
     */
    public static boolean mkdirs(String dirsName) {
        File file = new File(dirsName);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 创建多层级目录
     *
     * @param dirName
     * @return
     */
    public static boolean mkdirs(String... dirName) {
        if (dirName.length == 1) {
            return mkdir(dirName[0]);
        }
        StringBuilder dirs = new StringBuilder();
        for (String dir : dirName) {
            dirs.append(dir);
            if (!mkdir(dirs.toString())) {
                return false;
            }
            dirs.append("/");
        }
        return true;
    }

    /**
     * 创建多个目录层级文件夹
     *
     * @param dirs
     * @return
     */
    public static boolean mkdirsS(String... dirs) {
        if (dirs == null || dirs.length == 0) {
            return true;
        }
        for (String dir : dirs) {
            if (!mkdirs(dir)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取文件输入流
     *
     * @param path
     * @return
     */
    public static FileInputStream getFileInputStream(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            return fileInputStream;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * 删除一个文件
     *
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        if (file.isFile() && file.exists()) {
            file.delete();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 删除一个文件
     *
     * @param filePath
     */
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        return delete(file);
    }

    /**
     * 删除一个目录下的所有文件
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        //如果使目录的话，就递归处理
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files.length == 0) {
                return true;
            }
            for (File file : files) {
                if (file.isFile()) {
                    //删除文件
                    delete(file);
                } else {

                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 删除一个目录下的所有文件
     *
     * @param path
     * @return
     */
    public static boolean deleteDir(String path) {
        File dir = new File(path);
        return deleteDir(dir);
    }


    /**
     * 文件输出
     * @param fileLocation 下载的文件路径
     * @param resp
     * @throws Exception
     */
    public static void getFile(String fileLocation, HttpServletResponse resp) throws Exception {
        //从保存位置下载文件
        /*
         * servlet响应流的方式将文件下载到本地 以附件的方式另存文件
         */
        FileInputStream fis = new FileInputStream(fileLocation);
        resp.setCharacterEncoding("utf-8");
        BufferedInputStream bis = new BufferedInputStream(fis, 1024);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        byte[] cache = new byte[1024];
        int length = 0;
        while ((length = bis.read(cache)) != -1) {
            bos.write(cache, 0, length);
        }

        resp.getOutputStream().write(bos.toByteArray());

        fis.close();
        bis.close();
        bos.close();
    }
}
