package com.aaa.check.service.impl;

import com.aaa.check.dao.CheckItemDao;
import com.aaa.check.service.CheckItemService;
import com.aaa.check.vo.NewCareVo;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 检查费用表(SysCheckItem)表服务实现类
 *
 * @author makejava
 * @since 2023-03-24 17:32:14
 */
@Service
public class CheckItemServiceImpl implements CheckItemService {
   @Autowired
   private CheckItemDao checkItemDao;

   @Override
   public Result<List<CheckItem>> selectAllCheckItem() {
      //List<String> list = checkItemDao.selectAllCheckItem();
      QueryWrapper wrapper = new QueryWrapper<>();
      List<CheckItem> list = checkItemDao.selectList(wrapper);
      return new Result<>(2000, "查询项目名称", list);
   }

   //doctor调用根据关键字查询检查项目
   @Override
   public Page<CheckItem> selectByKey(NewCareVo newCareVo) {
      Page<CheckItem> page = new Page<>(newCareVo.getPageNum(), newCareVo.getPageSize());
      QueryWrapper wrapper = new QueryWrapper();
      if (StringUtils.hasText(newCareVo.getKeywords())) {
         wrapper.like("keywords", newCareVo.getKeywords());
      }
      return checkItemDao.selectPage(page, wrapper);
   }
}
