package com.aaa.system.service.Impl;

import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.RegisteredItemDao;
import com.aaa.system.service.RegisteredItemService;
import com.aaa.system.vo.RegisteredItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RegisteredItemServiceImpl implements RegisteredItemService {
    @Autowired
    private RegisteredItemDao dao;
    @Override
    public Result getAll(Page page, RegisteredItemVo registeredItemVo) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        if (registeredItemVo.getRegItemName()!=null){
            queryWrapper.like("reg_item_name",registeredItemVo.getRegItemName());
        }
        if (registeredItemVo.getStatus()!=null){
            queryWrapper.eq("status",registeredItemVo.getStatus());
        }
        Page page1 = dao.selectPage(page, queryWrapper);

        return new Result(200,null,page1);
    }

    @Override
    public Result saveAndUp(RegisteredItem registeredItem) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenChaim = JwtUtil.getTokenChaim(token);
        if (registeredItem.getRegItemId()==null){
            registeredItem.setCreateBy((String) tokenChaim.get("username2"));
            registeredItem.setCreateTime(new Date());
            registeredItem.setUpdateBy((String) tokenChaim.get("username2"));
            registeredItem.setUpdateTime(new Date());
            int insert = dao.insert(registeredItem);
            if (insert>0){
                return new Result(200,null,true);
            }else {
                return new Result(200,null,false);
            }
        }else {
            registeredItem.setUpdateBy((String) tokenChaim.get("username2"));
            registeredItem.setUpdateTime(new Date());
            int i = dao.updateById(registeredItem);
            if (i>0){
                return new Result(200,null,true);
            }else {
                return new Result(200,null,false);
            }
        }
    }

    @Override
    public Result delInfo(Long id) {
        int i = dao.deleteById(id);
        if (i>0){
            return new Result(200,null,true);
        }else {
            return new Result(200,null,false);
        }
    }
}
