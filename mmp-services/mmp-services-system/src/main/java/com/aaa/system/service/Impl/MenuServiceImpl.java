package com.aaa.system.service.Impl;

import com.aaa.core.entity.Menu;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.MenuDao;
import com.aaa.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao dao;
    @Override
    public List<Menu> findByUserid(Long userId) {
        System.out.println("----------------"+userId);
        return dao.findByUserid(userId);
    }

    @Override
    public Result<List<Menu>> findLefMenu() {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        List<Menu> phone = dao.findLefMenu((String) info.get("username"));
        List<Menu> collect = phone.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        for (Menu m:collect
             ) {
            m.setChildren(getChildren(m.getMenuId(),phone));
        }

        return new Result<List<Menu>>(200,"查询菜单成功",collect);
    }

    private List<Menu> getChildren(Long parentId,List<Menu> menu){
        List<Menu> seconds=new ArrayList<>();
        for (Menu m:menu) {
        if (m.getParentId().equals(parentId)){
            seconds.add(m);
            }
        }
        for (Menu m:seconds){
            m.setChildren(getChildren(m.getMenuId(),menu));
        }
        return seconds;
    }
}
