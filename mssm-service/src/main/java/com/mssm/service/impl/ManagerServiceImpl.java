package com.mssm.service.impl;

import com.mssm.domain.Manager;
import com.mssm.domain.QueryInfo;
import com.mssm.mapper.ManagerMapper;
import com.mssm.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> managerList(QueryInfo queryInfo) {

        // query pagenum pagesize 为空
        if(queryInfo.getQuery()==null && queryInfo.getPagenum()==null && queryInfo.getPagesize()==null){
            System.out.println(">>>>>>>>>>>>>>>>>>>>> 11");
            List<Manager> managerList = managerMapper.findAllManager();
            System.out.println(">>>>>>>>>>>>>>>>>>>>> managerList: " +managerList);
            return managerList;
        }
        // query 为空
        else if (queryInfo.getQuery()==null) {
            System.out.println("wait......");
        }
        // pagenum pagesize 是否为空
        else if (queryInfo.getPagenum()==null && queryInfo.getPagesize()==null) {
            System.out.println("wait......");
        }

        return null;
    }
}
