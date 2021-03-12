package com.mssm.service.impl;

import com.mssm.domain.Manager;
import com.mssm.domain.ManagerR;
import com.mssm.domain.QueryInfo;
import com.mssm.mapper.ManagerMapper;
import com.mssm.service.ManagerService;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.Md5;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> managerList(QueryInfo queryInfo) {

        // query pagenum pagesize 为空
        if(queryInfo.getQuery()==null && queryInfo.getPagenum()==null && queryInfo.getPagesize()==null){
            List<Manager> managerList = managerMapper.findAllManager();
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
        String jwt = JwtUtil.createJWT(managerR.getId().toString(), "mssm", System.currentTimeMillis() + 1000 * 60 * 60 * 24L);
        HttpSession session = request.getSession();
        session.setAttribute("token", jwt);
        managerR.setToken(jwt);
        return managerR;
    }
}
