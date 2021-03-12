package com.mssm.controller;

import com.mssm.domain.Manager;
import com.mssm.domain.Meta;
import com.mssm.domain.QueryInfo;
import com.mssm.domain.ResponseResult;
import com.mssm.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
