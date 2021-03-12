package com.mssm.service;

import com.mssm.domain.Manager;
import com.mssm.domain.QueryInfo;

import java.util.List;

public interface ManagerService {
    // 查询所有用户
    public List<Manager> managerList(QueryInfo queryInfo);
}
