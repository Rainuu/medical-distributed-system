package com.aaa.system.service;



import com.aaa.core.entity.Menu;
import com.aaa.core.vo.Result;

import java.util.List;

public interface MenuService {
    public List<Menu> findByUserid(Long userId);
    Result<List<Menu>> findLefMenu();
}
