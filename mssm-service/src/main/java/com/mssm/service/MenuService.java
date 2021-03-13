package com.mssm.service;

import com.mssm.domain.Menu;

import java.util.List;

public interface MenuService {
    // 查询所有菜单
    public List<Menu> findAllMenu();
}
