package com.aaa.system.dao;


import com.aaa.core.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface RoleDao extends BaseMapper<Role> {
    @Select("select r.* from sys_role r join sys_role_user ru on r.role_id=ru.role_id where ru.user_id=#{userId}")
    List<Role> selectByuserId(Long userId);

    @Delete(value = "delete  from sys_role_user where user_id=#{userId}")
    void deleteByuserId(Long userId);

    void batchInsert(@Param("userId") Long userId,@Param("roleIds") Long[] roleIds);
}
