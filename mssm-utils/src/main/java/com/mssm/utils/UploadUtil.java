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

    public static TrackerClient trackerClient;

    static {
        try {
            // 加载配置文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            // 创建tracker客户端
            trackerClient = new TrackerClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 一步上传到FastDFS
     * @param path
     * @param file
     * @param request
     * @param ip
     * @return
     * @throws Exception
     */
    public static Map<String,String> upload(String path,MultipartFile file, HttpServletRequest request, String ip) throws Exception{
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

        // 1.基本配置
        // 通过tracker客户端获取tracker的连接服务
        TrackerServer trackerServer = trackerClient.getConnection();
        // 声明storage服务
        StorageServer storageServer = null;
        // 定义storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        // 2.文件相关设置
        // 定义文件元信息
        NameValuePair[] list = new NameValuePair[1];
        list[0] = new NameValuePair("fileName",originalFilename);
        String fileID = client.upload_file1(
                newFilepath,
                originalFilename.substring(originalFilename.lastIndexOf(".")+1),
                list);
        // 3.释放资源
        trackerServer.close();
        // 3.返回结果
        // 7.响应数据
        Map<String,String> map = new HashMap<>();
        map.put("originalFilename", originalFilename);
        map.put("newFilename", newFilename);
        map.put("newFilepath", newFilepath);
        map.put("fastDFSFileId", fileID);
        map.put("fastDFSPath", "http://"+ip+"/"+fileID);
        return map;
    }

    /**
     * 一步删除文件
     * @param newPath
     * @param fileId
     * @return
     * @throws Exception
     */
    public static Boolean delete(String newPath, String fileId) throws Exception {
        Boolean delete = true;
        Integer i = null;
        // 本地删除
        File file = new File(newPath);
        if (file.exists()){
            delete = file.delete();
        } else {
            System.out.println("文件不存在");
        }

        // fdfs删除

        // 1.基本配置
        // 通过tracker客户端获取tracker的连接服务
        TrackerServer trackerServer = trackerClient.getConnection();
        // 声明storage服务
        StorageServer storageServer = null;
        // 定义storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        // 删除 groupName:group1 remoteFilename:M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
        String groupName = fileId.substring(0, fileId.indexOf("/"));
        String remoteFilename = fileId.substring(fileId.indexOf("/")+1);
        i = client.delete_file(groupName, remoteFilename);//groupName + remoteFilename

        trackerServer.close();
        // 判断两个删除是否成功 false:删除失败 true:删除成功 null:无此文件
        // 释放资源 0:成功删除
        return delete && i == 0;
    }

    /**
     * 文件上传到web服务器 + 返回文件信息
     * @param path
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
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

    /**
     * 文件上传到FastDFS
     * @param originalFilename
     * @param newFilepath
     * @return
     * @throws Exception
     */
    public static String fdfsUpload(String originalFilename, String newFilepath) throws Exception{

        // 1.基本配置
        // 通过tracker客户端获取tracker的连接服务
        TrackerServer trackerServer = trackerClient.getConnection();
        // 声明storage服务
        StorageServer storageServer = null;
        // 定义storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        // 2.文件相关设置
        // 定义文件元信息
        NameValuePair[] list = new NameValuePair[1];
        list[0] = new NameValuePair("fileName",originalFilename);
        String fileID = client.upload_file1(
                newFilepath,
                originalFilename.substring(originalFilename.lastIndexOf(".")+1),
                list);
        // 3.释放资源
        trackerServer.close();
        // 3.返回结果
        return fileID;
    }

    /**
     * 删除本地图片
     * @param newPath   文件所在路径
     * @return          true:成功 false:失败
     */
    public static Boolean fileDelete(String newPath){
        boolean delete = true;
        // 本地删除
        File file = new File(newPath);
        if (file.exists()){
            delete = file.delete();
        } else {
            System.out.println("文件不存在");
        }
        return delete;
    }

    /**
     * 删除fdfs图片
     * @param fileId    FastDFS的fileId
     * @return          true:成功 false:失败
     * @throws Exception 异常
     */
    public static Boolean fdfsDelete(String fileId) throws Exception {
        // 1.基本配置
        // 通过tracker客户端获取tracker的连接服务
        TrackerServer trackerServer = trackerClient.getConnection();
        // 声明storage服务
        StorageServer storageServer = null;
        // 定义storage客户端
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
        // 删除 groupName:group1 remoteFilename:M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
        String groupName = fileId.substring(0, fileId.indexOf("/"));
        String remoteFilename = fileId.substring(fileId.indexOf("/")+1);
        int i = client.delete_file(groupName, remoteFilename);//groupName + remoteFilename
        // 释放资源
        trackerServer.close();
        return i == 0;
    }

}
