package com.mssm.mapper;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;

import java.util.List;

public interface ManagerMapper {
    // 查询所有管理者
    public List<Manager> findAllManager();
    // 通过名称或手机号查询管理员
    public ManagerR findManagerByNameOrPhone(Manager manager);
}
