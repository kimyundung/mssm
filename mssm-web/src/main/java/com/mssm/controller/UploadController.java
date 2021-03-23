package com.mssm.controller;

import com.mssm.domain.FileInfoWEB;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.UploadUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传与下载
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final String AUTH = "Authorization";

    @RequestMapping("/file")
    public ResponseResult file(FileInfoWEB fileInfoWEB, @RequestParam("file")MultipartFile file, HttpServletRequest request){

        Boolean verify = JwtUtil.verify(request.getHeader(AUTH));
        ResponseResult result = new ResponseResult();

        if(verify){
            try {
                // 如果存在上次上传图片, 删除上次上传的图片
                if (fileInfoWEB.getNewFilepath()!=null){
                    UploadUtil.deleteFile(fileInfoWEB.getNewFilepath());
                }
                // 图片上传到web服务器
                Map<String, String> map = UploadUtil.fileUpload("mssm", file, request);
                // 响应数据
                result.setData(map);
                result.setMeta(new Meta(200,"成功上传文件到web服务器"));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setMeta(new Meta(500,"失败上传文件到web服务器, 产生意外"));
                return result;
            }
        }
        // token失效
        result.setMeta(new Meta(500, "失败上传文件到web服务器, token失效"));
        return result;
    }

}
