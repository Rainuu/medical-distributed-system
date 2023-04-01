package com.aaa.system.service.Impl;

import com.aaa.core.entity.Notice;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.NoticeDao;
import com.aaa.system.service.NoticeService;
import com.aaa.system.vo.NoticeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lml
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao dao;
    @Override
    public Result getAll(Page page, NoticeVo noticeVo) {
        QueryWrapper queryWrapper =new QueryWrapper<>();
        if (noticeVo.getNoticeTitle()!=null&&noticeVo.getNoticeTitle()!=""){
            queryWrapper.like("notice_title",noticeVo.getNoticeTitle());
        }
        if (noticeVo.getNoticeType()!=null){
            queryWrapper.eq("notice_type",noticeVo.getNoticeType());
        }
        if (noticeVo.getCreateBy()!=null&&noticeVo.getCreateBy()!=""){
            queryWrapper.like("create_by",noticeVo.getCreateBy());
        }
        if (noticeVo.getStatus()!=null){
            queryWrapper.eq("status",noticeVo.getStatus());
        }

        Page page1 = dao.selectPage(page, queryWrapper);
        return new Result(200,null,page1);
    }

    @Override
    public Result saveAndUp(Notice notice) {
        if (notice.getNoticeId()==null){
            notice.setCreateTime(new Date());
            notice.setUpdateTime(new Date());

            int insert = dao.insert(notice);
            if (insert>0){
                return new Result(200,null,true);
            }
            return new Result(200,null,false);
        }else {
            notice.setUpdateTime(new Date());
            int i = dao.updateById(notice);
            if (i>0){
                return new Result(200,null,true);
            }
            return new Result(200,null,false);
        }
    }

    @Override
    public Result delById(Long id) {
        int i = dao.deleteById(id);
        if (i>0){
            return new Result(200,null,true);
        }
        return new Result(200,null,false);
    }
}
