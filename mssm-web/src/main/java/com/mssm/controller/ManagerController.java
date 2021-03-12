package com.mssm.controller;

import com.mssm.domain.*;
import com.mssm.service.ManagerService;
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
    public ResponseResult managerList(@RequestBody QueryInfo queryInfo){
        System.out.println(">>>>>>>>>>>> queryInfo : " + queryInfo);
        ResponseResult result = new ResponseResult();
        try{
            List<Manager> managerList = managerService.managerList(queryInfo);
            if(managerList != null) {
                result.setData(managerList);
                result.setMeta(new Meta(200,"查询成功"));
            } else {
                result.setMeta(new Meta(500,"查询失败, 参数有误"));
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"查询失败, 产生意外"));
        } finally {
            return result;
        }
    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody Manager manager, HttpServletRequest request){
        ResponseResult result = new ResponseResult();
        try {
            ManagerR managerR = managerService.login(manager,request);
            if (managerR == null){
                result.setMeta(new Meta(500,"登陆失败, 用户名或密码错误"));
            } else {
                result.setData(managerR);
                result.setMeta(new Meta(200,"登陆成功"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(501,"登陆失败, 出现异常"));
        } finally {
            return result;
        }
    }

}
