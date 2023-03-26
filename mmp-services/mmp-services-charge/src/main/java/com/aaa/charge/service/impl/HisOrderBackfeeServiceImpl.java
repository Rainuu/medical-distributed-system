package com.aaa.charge.service.impl;

import com.aaa.charge.fegin.OrderCharFeign;
import com.aaa.charge.service.HisOrderBackfeeService;
import com.aaa.charge.dao.HisOrderBackfeeMapper;


import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表服务实现类
 *
 * @author makejava
 * @since 2023-03-25 11:09:21
 */
@Service
public class HisOrderBackfeeServiceImpl implements HisOrderBackfeeService {
   @Autowired
   private HisOrderBackfeeMapper hisOrderBackfeeDao;
   @Autowired
   private OrderCharFeign orderCharFeign;

   @Override
   //分页模糊查询所有数据
   public Result<IPage<OrderBackfee>> findAll(Integer curr, Integer size, OrderBackfeeVo backfeeVo) {
      IPage<OrderBackfee> page = new Page<>(curr,size);
      QueryWrapper<OrderBackfee> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(backfeeVo.getPatientName())){
         wrapper.like("patient_name",backfeeVo.getPatientName());
      }
      if (StringUtils.hasText(backfeeVo.getRegId())){
         wrapper.like("reg_id",backfeeVo.getRegId());
      }
      IPage<OrderBackfee> page1 = hisOrderBackfeeDao.selectPage(page,wrapper);
      return new Result<>(200,"查询成功",page1);
   }

   @Override
   //查询所有数据根据id返回一条数据
   public Result<List<OrderBackfeeItem>> selectAll(String backId) {
      List<OrderBackfeeItem> select =hisOrderBackfeeDao.selectAll(backId);
      System.out.println("nadioghaiodhfeaiohaeioghiodbguigadbuiodhb"+select);
      return new Result<>(200,"查询成功",select);
   }

   @Override
   //调用系统模块完成字典查询
   public Result<List<DictData>> selectdictionary() {
      Result<List<DictData>> findall= new Result<>(200,"字典查询成功",orderCharFeign.getAll());
      return findall;
   }


}
