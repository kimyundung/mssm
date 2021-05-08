package com.mssm.controller;

import com.mssm.domain.File;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.domain.Swiper;
import com.mssm.service.FileService;
import com.mssm.service.SwiperService;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SwiperService swiperService;

    //上传头像图片
    @RequestMapping("/uploadPortraitPic")
    public ResponseResult uploadPortraitPic(File fileInfo, @RequestParam("file")MultipartFile file, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>uploadGP fileInfo: " + fileInfo);
        ResponseResult result = new ResponseResult();

        try {
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

    // 轮播图上传并添加到数据库
    @RequestMapping("/uploadSwiperPic")
    public ResponseResult uploadSwiperPic(File fileInfo, @RequestParam("file") MultipartFile file, HttpServletRequest request){
        //System.out.println(">>>>>>>>>>>>>>> fileInfo: "+fileInfo);
        ResponseResult result = new ResponseResult();
        try {
            // 图片上传
            Map<String, String> map = UploadUtil.upload("mssm", file, request,"106.75.253.40");
            // 保存到数据库
            Swiper swiper = new Swiper();
            swiper.setName(map.get("originalFilename"));
            swiper.setFileId(map.get("fastDFSFileId"));
            swiper.setUrl(map.get("fastDFSPath"));
            swiperService.add(swiper);
            result.setData(swiper);
            result.setMeta(new Meta(200,"成功上传轮播图"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败上传轮播图, 产生意外"));
            return result;
        }
    }

    @RequestMapping("/uploadGoodsPic")
    public ResponseResult goodsPicUpload(File fileInfo, @RequestParam("file")MultipartFile file, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>uploadGP fileInfo: " + fileInfo);
        ResponseResult result = new ResponseResult();

        try {
            // 图片上传(本地+fdfs)
            Map<String, String> map = UploadUtil.upload("mssm", file, request,"106.75.253.40");
            // topPic==0 && id存在 保存到数据库
            /*if(fileInfo.getTopPic()!=null && fileInfo.getGid()>0){
                fileInfo.setOriginalFilename(map.get("originalFilename"));
                fileInfo.setNewFilename(map.get("newFilename"));
                fileInfo.setNewFilepath(map.get("newFilepath"));
                fileInfo.setFastDFSFileId(map.get("fastDFSFileId"));
                fileInfo.setFastDFSPath(map.get("fastDFSPath"));
                fileService.addFile(fileInfo);
                map.put("id",fileInfo.getId().toString());
            }*/
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

    /**
     * 删除图片
     * - 点击按钮删除
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public ResponseResult delete(@RequestBody File file, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>>>>>>>>delete fileInfo: " + file );
        ResponseResult result = new ResponseResult();

        try {

            // 删除图片(本地+fdfs)
            Boolean delete = true;
            if (file!=null && file.getNewFilepath()!=null && file.getFastDFSFileId()!=null){
                delete = UploadUtil.delete(file.getNewFilepath(), file.getFastDFSFileId());
            }

            // topPic==0 && id存在 从数据库删除
            /*if(file.getId()>0){
                fileService.deleteFile(file.getId());
            }*/

            // 响应结构
            if(delete){
                result.setMeta(new Meta(200,"成功删除文件"));
            }else {
                result.setMeta(new Meta(501,"失败删除文件"));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除文件, 产生意外"));
            return result;
        }

    }
    /**
     * 上传图片
     * - 头像(删除+上传)
     * - 直接上传
     * @param fileInfo
     * @param file
     * @param request
     * @return
     */
//    @RequestMapping("/upload")
//    public ResponseResult upload(File fileInfo, @RequestParam("file")MultipartFile file, HttpServletRequest request){
//        System.out.println(">>>>>>>>>>>>>upload fileInfo: " + fileInfo);
//        ResponseResult result = new ResponseResult();
//
//        try {
//            /*// 删除图片(本地)
//            if (fileInfo!=null && fileInfo.getNewFilepath()!=null){
//                UploadUtil.fileDelete(fileInfo.getNewFilepath());
//            }
//            // 图片上传(本地)
//            Map<String, String> map = UploadUtil.fileUpload("mssm", file, request);*/
//
//            // 删除图片(本地+fdfs)
//            if (fileInfo!=null && fileInfo.getFastDFSFileId()!=null && fileInfo.getNewFilepath()!=null){
//                UploadUtil.delete(fileInfo.getNewFilepath(),fileInfo.getFastDFSFileId());
//            }
//            // 图片上传(本地+fdfs)
//            Map<String, String> map = UploadUtil.upload("mssm", file, request,"106.75.253.40");
//
//            // 响应数据
//            result.setData(map);
//            result.setMeta(new Meta(200,"成功上传文件"));
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setMeta(new Meta(500,"失败上传文件, 产生意外"));
//            return result;
//        }
//
//    }

}
