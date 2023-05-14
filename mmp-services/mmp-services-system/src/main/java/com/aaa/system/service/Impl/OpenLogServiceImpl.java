package com.aaa.system.service.Impl;

import com.aaa.core.vo.Result;
import com.aaa.system.dao.OpenLogDao;
import com.aaa.system.service.OpenLogService;
import com.aaa.system.vo.OpenLogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenLogServiceImpl implements OpenLogService {
    @Autowired
    private OpenLogDao dao;

    @Override
    public Result getAll(Page page, OpenLogVo openLogVo) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (openLogVo.getBusinessType() != null) {
            queryWrapper.like("business_type", openLogVo.getBusinessType());
        }
        if (openLogVo.getOperName() != null) {
            queryWrapper.like("oper_name", openLogVo.getOperName());
        }
        if (openLogVo.getTitle() != null) {
            queryWrapper.like("title", openLogVo.getTitle());
        }
        if (openLogVo.getStatus() != null) {
            queryWrapper.eq("status", openLogVo.getStatus());
        }
        if (openLogVo.getDateRange() != null) {
            queryWrapper.between("oper_time", openLogVo.getDateRange()[0], openLogVo.getDateRange()[1]);
        }
        Page page1 = dao.selectPage(page, queryWrapper);
        return new Result(200, null, page1);
    }


    @Override
    public Result delOperLog(Long id) {
        int i = dao.deleteById(id);
        if (i > 0) {
            return new Result(200, null, true);
        }
        return new Result(200, null, false);
    }

    @Override
    public Result clean() {
        int delete = dao.delete(null);

        if (delete > 0) {
            return new Result(200, "删除成功", delete);
        }
        return new Result(200, null, false);
    }
}
