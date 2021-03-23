package com.mssm.service;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;
import com.mssm.domain.ManagerVO;
import com.mssm.domain.QueryInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerService {
    // 查询所有管理员
    public List<Manager> managerList(QueryInfo queryInfo);
    // 管理员登陆
    public ManagerR login(Manager manager, HttpServletRequest request) throws Exception;
    // 修改管理员状态
    public void changeManagerStatus(Manager manager);
    // 添加管理员
    public void saveManager(ManagerVO managerVO) throws Exception;
    // 编辑管理员
    public void updateManager(ManagerVO managerVO) throws Exception;
    // 编辑密码
    public void updatePassword(Manager manager) throws Exception;
    // 验证管理者名
    public Integer checkName(String name);
    // 删除用户
    public void deleteManager(Integer id);
}
