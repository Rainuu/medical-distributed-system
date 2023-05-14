package com.aaa.system.service.Impl;

import com.aaa.core.entity.CheckItem;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.CheckItemDao;
import com.aaa.system.service.CheckItemService;
import com.aaa.system.vo.CheckItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao dao;

    @Override
    public Result getAll(Page page, CheckItemVo checkItemVo) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (checkItemVo.getCheckItemName() != null) {
            queryWrapper.like("check_item_name", checkItemVo.getCheckItemName());
        }
        if (checkItemVo.getKeywords() != null) {
            queryWrapper.like("keywords", checkItemVo.getKeywords());
        }
        if (checkItemVo.getTypeId() != null) {
            queryWrapper.eq("type_id", checkItemVo.getTypeId());
        }
        if (checkItemVo.getStatus() != null) {
            queryWrapper.eq("status", checkItemVo.getStatus());
        }
        Page page1 = dao.selectPage(page, queryWrapper);

        return new Result(200, null, page1);
    }

    @Override
    public Result saveAndUp(CheckItem checkItem) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenChaim = JwtUtil.getTokenChaim(token);
        if (checkItem.getCheckItemId() == null) {
            checkItem.setCreateBy((String) tokenChaim.get("username2"));
            checkItem.setCreateTime(new Date());
            checkItem.setUpdateBy((String) tokenChaim.get("username2"));
            checkItem.setUpdateTime(new Date());
            int insert = dao.insert(checkItem);
            if (insert > 0) {
                return new Result(200, null, true);
            } else {
                return new Result(200, null, false);
            }
        } else {
            checkItem.setUpdateBy((String) tokenChaim.get("username2"));
            checkItem.setUpdateTime(new Date());
            int i = dao.updateById(checkItem);
            if (i > 0) {
                return new Result(200, null, true);
            } else {
                return new Result(200, null, false);
            }
        }
    }

    @Override
    public Result delInfo(Long id) {
        int i = dao.deleteById(id);
        if (i > 0) {
            return new Result(200, null, true);
        } else {
            return new Result(200, null, false);
        }
    }
}
