package com.mssm.controller;

import com.mssm.domain.File;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.UploadUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 文件上传,删除
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    private static final String AUTH = "Authorization";

    /**
     * 上传图片
     * - 头像(删除+上传)
     * - 直接上传
     * @param fileInfo
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public ResponseResult file(File fileInfo, @RequestParam("file")MultipartFile file, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>上传图片 " + fileInfo);

        Boolean verify = JwtUtil.verify(request.getHeader(AUTH));
        ResponseResult result = new ResponseResult();

        if(verify){
            try {
                /*// 删除图片(本地)
                if (fileInfo!=null && fileInfo.getNewFilepath()!=null){
                    UploadUtil.fileDelete(fileInfo.getNewFilepath());
                }
                // 图片上传(本地)
                Map<String, String> map = UploadUtil.fileUpload("mssm", file, request);*/

                // 删除图片(本地+fdfs)
                if (fileInfo!=null && fileInfo.getFastDFSFileId()!=null && fileInfo.getNewFilepath()!=null){
                    UploadUtil.delete(fileInfo.getNewFilepath(),fileInfo.getFastDFSFileId());
                }
                // 图片上传(本地+fdfs)
                Map<String, String> map = UploadUtil.upload("mssm", file, request,"106.75.253.40");

                // 响应数据
                result.setData(map);
                result.setMeta(new Meta(200,"成功上传文件"));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setMeta(new Meta(500,"失败上传文件, 产生意外"));
                return result;
            }
        }
        // token失效
        result.setMeta(new Meta(500, "失败上传文件, token失效"));
        return result;
    }

    /**
     * 删除图片
     * - 点击按钮删除
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public ResponseResult delete(@RequestBody File file, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>delete " + file);

        Boolean verify = JwtUtil.verify(request.getHeader(AUTH));
        ResponseResult result = new ResponseResult();

        if(verify){
            try {

                /*// 删除图片(本地)
                Boolean delete = true;
                if (file!=null && file.getNewFilepath()!=null){
                    delete = UploadUtil.fileDelete(file.getNewFilepath());
                }*/

                // 删除图片(本地+fdfs)
                Boolean delete = true;
                if (file!=null && file.getNewFilepath()!=null && file.getFastDFSFileId()!=null){
                    delete = UploadUtil.delete(file.getNewFilepath(), file.getFastDFSFileId());
                }

                // 响应结构
                if(delete){
                    result.setMeta(new Meta(200,"成功删除文件"));
                }else {
                    result.setMeta(new Meta(500,"失败删除文件"));
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setMeta(new Meta(500,"失败删除文件, 产生意外"));
                return result;
            }
        }
        // token失效
        result.setMeta(new Meta(500, "失败删除文件, token失效"));
        return result;
    }
}
