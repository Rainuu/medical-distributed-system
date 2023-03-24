package com.aaa.charge.service.impl;

import com.aaa.charge.dao.HisOrderChargeItemMapper;
import com.aaa.charge.dao.HisOrderChargeMapper;
import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 收费表(HisOrderCharge)表服务实现类
 *
 * @author makejava
 * @since 2023-03-24 09:56:37
 */
@Service
public class HisOrderChargeServiceImpl implements HisOrderChargeService {
   @Autowired
   private HisOrderChargeMapper hisOrderChargeMapper;
   @Autowired
   private HisOrderChargeItemMapper hisOrderChargeItemMapper;
   @Override
   public Result<IPage<OrderCharge>> findAll(Integer curr, Integer size, OrderChargeVo chargeVo) {
      IPage<OrderCharge> page = new Page(curr,size);
      QueryWrapper<OrderCharge> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(chargeVo.getPatientName())){
         wrapper.like("patient_name",chargeVo.getPatientName());
      }
      if (StringUtils.hasText(chargeVo.getRegId())){
         wrapper.like("reg_id",chargeVo.getRegId());
      }
      IPage<OrderCharge> orderChargeIPage = hisOrderChargeMapper.selectPage(page, wrapper);
      return new Result<>(200,"查询成功",orderChargeIPage);
   }

   @Override
   public Result<List<OrderChargeItem>> finddetail(String orderId) {
      List<OrderChargeItem> orderChargeItems = hisOrderChargeItemMapper.selectListAll(orderId);
      return new Result<>(200,"查询成功",orderChargeItems);
   }
}
