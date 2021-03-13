package com.mssm.mapper;

import com.mssm.domain.Menu;

import java.util.List;

public interface MenuMapper {
    // 查询所有菜单
    public List<Menu> findAllMenu();
    // 根据父级id查询子菜单
    public List<Menu> findSubMenuByPid();
}
