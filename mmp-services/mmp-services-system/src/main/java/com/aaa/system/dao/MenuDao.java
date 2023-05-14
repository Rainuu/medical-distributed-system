package com.aaa.system.dao;

import com.aaa.core.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    @Select(value = "select m.* from sys_role_menu rm,sys_role_user ru,sys_menu m where rm.role_id=ru.role_id and rm.menu_id=m.menu_id and ru.user_id=#{userId}")
    List<Menu> findByUserid(Long userId);

    @Select(value = "select distinct m.* from sys_role_user ru join sys_role_menu rm on ru.role_id=rm.role_id join sys_menu m on rm.menu_id=m.menu_id " +
            "join sys_user u on u.user_id=ru.user_id where u.phone=#{phone}")
    List<Menu> findLefMenu(String phone);

    @Select(value = "select menu_id from sys_role_menu where role_id=#{roleId}")
    List<Integer> getByRoleId(Long roleId);

    @Delete(value = "delete from sys_role_menu where role_id=#{roleId}")
    void deleteByRoleId(Long roleId);

    void batchMenus(@Param("roleId") Long roleId, @Param("menuIds") Long[] menuIds);
}
