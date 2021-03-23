package com.mssm.service.impl;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;
import com.mssm.domain.ManagerVO;
import com.mssm.domain.QueryInfo;
import com.mssm.mapper.ManagerMapper;
import com.mssm.service.ManagerService;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.Md5;
import com.mssm.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private static final String KEY = "misssimple";

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> managerList(QueryInfo queryInfo) {
        List<Manager> managerList = managerMapper.findAllManager(queryInfo.getQuery());
        return managerList;
    }

    @Override
    public ManagerR login(Manager manager, HttpServletRequest request) throws Exception {
        /*
            1.查询用户
                不在: 返回用户不存在
                存在: 验证密码
                    通过: token
         */
        ManagerR managerR = managerMapper.findManagerByNameOrPhone(manager);
        // 1.管理员不存在返回null
        if(managerR == null) {
            return null;
        }
        // 2.管理员存在 验证密码
        String md5Password = Md5.md5(manager.getPassword(), "misssimple");
        boolean verify = Md5.verify(manager.getPassword(), "misssimple", managerR.getPassword());
        // 2.1 验证失败
        if (!verify){
            return null;
        }
        // 2.2 验证成功
        managerR.setPassword(null);
        // 2.2.1 token
        String jwt = JwtUtil.createJWT(managerR.getId().toString(), "mssm", 1000*60*60*24L);
        HttpSession session = request.getSession();
        session.setAttribute("token", jwt);
        managerR.setToken(jwt);
        return managerR;
    }

    @Override
    public void changeManagerStatus(Manager manager) {
        manager.setUpdatetime(new Date());
        managerMapper.changeManagerStatus(manager);
    }

    @Override
    public void saveManager(ManagerVO managerVO) throws Exception {
        // 处理头像
        if(managerVO.getNewFilepath()!=null){
            String fileId = UploadUtil.fdfsUpload(managerVO.getOriginalFilename(), managerVO.getNewFilepath());
            managerVO.setPortrait("http://www.aaa.com/"+fileId);
        }
        // 密码
        managerVO.setPassword(Md5.md5(managerVO.getPassword(), KEY));
        managerMapper.saveManager(managerVO);
    }

    @Override
    public void updateManager(ManagerVO managerVO) throws Exception {
        // 处理头像
        if(managerVO.getNewFilepath()!=null){
            String fileId = UploadUtil.fdfsUpload(managerVO.getOriginalFilename(), managerVO.getNewFilepath());
            managerVO.setPortrait("http://www.aaa.com/"+fileId);
        }
        managerVO.setUpdatetime(new Date());
        managerMapper.updateManager(managerVO);
    }

    @Override
    public void updatePassword(Manager manager) throws Exception {
        manager.setUpdatetime(new Date());
        manager.setPassword(Md5.md5(manager.getPassword(),KEY));
        managerMapper.updatePassword(manager);
    }

    @Override
    public Integer checkName(String name) {
        return managerMapper.checkName(name);
    }

    @Override
    public void deleteManager(Integer id) {
        Manager manager = new Manager();
        manager.setId(id);
        manager.setDeletetime(new Date());
        manager.setPhone(String.valueOf(System.currentTimeMillis()));
        managerMapper.deleteManager(manager);
    }

}
