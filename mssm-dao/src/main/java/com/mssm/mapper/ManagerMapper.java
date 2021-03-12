package com.mssm.mapper;

import com.mssm.domain.Manager;

import java.util.List;

public interface ManagerMapper {
    // 查询所有管理者
    public List<Manager> findAllManager();
}
