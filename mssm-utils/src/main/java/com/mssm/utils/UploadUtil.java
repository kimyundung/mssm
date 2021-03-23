package com.mssm.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadUtil {

    public static StorageClient1 getClient() throws IOException, MyException {

        // 加载配置文件
        ClientGlobal.initByProperties("fastdfs-client.properties");
        // 创建tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        // 通过tracker客户端获取tracker的连接服务并返回
        TrackerServer trackerServer = trackerClient.getConnection();
        // 声明storage服务
        StorageServer storageServer = null;
        // 定义storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        return client;
    }

    // 文件上传到FastDFS
    public static String fdfsUpload(String originalFilename, String newFilepath) throws Exception{

        // 1.基本配置
        StorageClient1 client = UploadUtil.getClient();

        // 2.文件相关设置
        // 定义文件元信息
        NameValuePair[] list = new NameValuePair[1];
        list[0] = new NameValuePair("fileName",originalFilename);
        String fileID = client.upload_file1(
                newFilepath,
                originalFilename.substring(originalFilename.lastIndexOf(".")+1),
                list);

        // 3.返回结果
        return fileID;
    }


    // 文件上传到web服务器 + 返回文件信息
    public static Map<String,String> fileUpload(String path,MultipartFile file, HttpServletRequest request) throws IOException {
        // 1.判断接收到的文件是否为空
        // 2.获取原文件名
        // 3.生成新文件名
        // 4.获取项目部署路径(ssm-web在tomcat上的部署路径 需要HttpServletRequest)
        // 5.文件上传
        // 6.获取新文件路径
        // 7.响应数据

        // 1.判断接收到的文件是否为空
        if(file==null){
            throw new RuntimeException("空文件");
        }

        // 2.获取原文件名
        String originalFilename = file.getOriginalFilename();

        // 3.生成新文件名
        String newFilename = System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        // 4.获取项目部署路径(mssm在tomcat上的部署路径 需要HttpServletRequest)
        String realPath = request.getServletContext().getRealPath("/");
        realPath = realPath.substring(0,realPath.indexOf(path));

        // 5.文件上传
        String uploadPath = realPath + "upload\\";
        File filePath = new File(uploadPath, newFilename);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        file.transferTo(filePath);

        // 6.获取新文件路径
        String newFilepath = filePath.getAbsolutePath();

        // 7.响应数据
        Map<String,String> map = new HashMap<>();
        map.put("originalFilename", originalFilename);
        map.put("newFilename", newFilename);
        map.put("newFilepath", newFilepath);
        return map;
    }

    // 删除文件
    public static void deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.exists()){
            file.delete();
        } else {
            System.out.println("文件不存在");
        }
    }
}
