package com.aaa.system.service.Impl;

import com.aaa.core.entity.Menu;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.MenuDao;
import com.aaa.system.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public Result<List<Menu>> getAll(String menuName,Long status) {
        List<Menu> list=null;
        if (status!=null&&menuName!=null){
            QueryWrapper queryWrapper=new QueryWrapper<>();
            queryWrapper.like("menu_name",menuName);
            queryWrapper.eq("status",status);
            list = dao.selectList(queryWrapper);
        }else {
           list= dao.selectList(null);
        }
        List<Menu> collect = list.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        for (Menu m:collect
        ) {
            m.setChildren(getChildren(m.getMenuId(),list));
        }

        return new Result<List<Menu>>(200,"查询菜单成功",collect);
    }

    @Override
    public Result<List<Integer>> findByRoleId(Long roleId) {
        List<Integer> menuIds= dao.getByRoleId(roleId);
        return new Result<>(2000,"查询选中的菜单id",menuIds);
    }

    @Override
    public Result<String> confirmMenu(Long roleId, Long[] menuIds) {
        //roleId删除具有的菜单id
        dao.deleteByRoleId(roleId);
        //批量添加
        dao.batchMenus(roleId,menuIds);
        return new Result<>(2000,"分配成功");
    }

    @Override
    public int addMenu(Menu menu) {
        menu.setParentIds(String.valueOf(menu.getParentId()));
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenChaim = JwtUtil.getTokenChaim(token);
        menu.setCreateBy((String) tokenChaim.get("username"));
        menu.setCreateTime(new Date());
        menu.setUpdateBy((String) tokenChaim.get("username"));
        menu.setUpdateTime(new Date());
        int insert = dao.insert(menu);
        return insert;
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
