package com.mssm.service.impl;

import com.mssm.domain.Menu;
import com.mssm.mapper.MenuMapper;
import com.mssm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> menuList = menuMapper.findAllMenu();
        return menuList;
    }
}
