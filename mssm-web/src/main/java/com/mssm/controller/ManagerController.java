package com.mssm.controller;

import com.mssm.domain.*;
import com.mssm.service.ManagerService;
import com.mssm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/managerList")
    public ResponseResult managerList(QueryInfo queryInfo, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            List<Manager> managerList = managerService.managerList(queryInfo);
            if(managerList != null) {
                result.setData(managerList);
                result.setMeta(new Meta(200,"查询成功"));
                return result;
            } else {
                result.setMeta(new Meta(500,"用户列表查询失败, 参数有误"));
                return result;
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"用户列表查询失败, 产生意外"));
            return result;
        }

    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody Manager manager, HttpServletRequest request){
        ResponseResult result = new ResponseResult();
        try {
            ManagerR managerR = managerService.login(manager,request);
            if (managerR == null){
                result.setMeta(new Meta(500,"登录失败, 用户名或密码错误"));
                return result;
            } else {
                result.setData(managerR);
                result.setMeta(new Meta(200,"登录成功"));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(501,"登录失败, 出现异常"));
            return result;
        }
    }

    @RequestMapping("/managerStatus")
    public ResponseResult managerStatus(Manager manager,HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try {
            managerService.changeManagerStatus(manager);
            result.setMeta(new Meta(200,"状态修改成功"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"状态修改失败, 产生意外"));
            return result;
        }

    }

    @RequestMapping("/saveManager")
    public ResponseResult saveManager(@RequestBody ManagerVO managerVO, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 添加管理员
            if(managerVO.getId()==null){
                managerService.saveManager(managerVO);
                result.setMeta(new Meta(201,"添加管理员成功"));
            }
            // 编辑管理员
            else {
                managerService.updateManager(managerVO);
                result.setMeta(new Meta(201,"编辑管理员成功"));
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            if(managerVO.getId()==null){
                result.setMeta(new Meta(500,"添加管理员失败, 产生意外"));
            } else {
                result.setMeta(new Meta(500,"编辑管理员失败, 产生意外"));
            }
            return result;
        }

    }

    @RequestMapping("/updatePassword")
    public ResponseResult updatePassword(@RequestBody Manager manager, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            managerService.updatePassword(manager);
            result.setMeta(new Meta(201,"编辑密码成功"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"编辑密码失败, 产生意外"));
            return result;
        }

    }

    @RequestMapping("/checkName")
    public ResponseResult checkName(String name,HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            int cnt = managerService.checkName(name);
            if(cnt == 0) {
                result.setMeta(new Meta(200,"该用户名可用"));
            } else {
                result.setMeta(new Meta(502,"该用户以存在"));
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"验证用户名失败, 产生意外"));
            return result;
        }

    }

    @RequestMapping("/deleteManager")
    public ResponseResult deleteManager(Integer id, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            managerService.deleteManager(id);
            result.setMeta(new Meta(202,"删除用户成功"));
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"删除用户失败,产生意外"));
            return result;
        }

    }
}
