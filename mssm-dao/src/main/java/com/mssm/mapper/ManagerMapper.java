package com.mssm.mapper;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;

import java.util.List;

public interface ManagerMapper {
    // 查询所有管理者
    public List<Manager> findAllManager(String query);
    // 通过名称或手机号查询管理员
    public ManagerR findManagerByNameOrPhone(Manager manager);
    // 修改管理员状态
    public void changeManagerStatus(Manager manager);
    // 添加管理员
    public void saveManager(Manager manager);
    // 编辑管理员
    public void updateManager(Manager manager);
    // 编辑密码
    public void updatePassword(Manager manager);
    // 验证管理者名
    public Integer checkName(String name);
    // 逻辑删除管理员
    public void deleteManager(Manager manager);
}
