package com.aaa.system.service;


import com.aaa.core.entity.Menu;
import com.aaa.core.vo.Result;

import java.util.List;

public interface MenuService {
    List<Menu> findByUserid(Long userId);

    Result<List<Menu>> findLefMenu();

    Result<List<Menu>> findMenu();

    Result<List<Menu>> getAll(String menuName, Long status);

    Result<List<Integer>> findByRoleId(Long roleId);

    Result<String> confirmMenu(Long roleId, Long[] menuIds);

    int addMenu(Menu menu);

    int delMenu(Long id);
}
