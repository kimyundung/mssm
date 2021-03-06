package com.mssm.controller;

import com.mssm.domain.Menu;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.service.MenuService;
import com.mssm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("findAllMenu")
    public ResponseResult findAllMenu(HttpServletRequest request) {
        ResponseResult result = new ResponseResult();

            try {
                // 获取菜单
                List<Menu> menuList = menuService.findAllMenu();
                if (menuList != null) {
                    result.setData(menuList);
                    result.setMeta(new Meta(200, "获取成功"));
                } else {
                    result.setMeta(new Meta(500, "获取失败, null"));
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                result.setMeta(new Meta(500, "获取失败, 出现异常"));
                return result;
            }
    }
}
