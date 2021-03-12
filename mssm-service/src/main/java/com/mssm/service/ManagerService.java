package com.mssm.service;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;
import com.mssm.domain.QueryInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerService {
    // 查询所有管理员
    public List<Manager> managerList(QueryInfo queryInfo);
    // 管理员登陆
    public ManagerR login(Manager manager, HttpServletRequest request) throws Exception;
}
