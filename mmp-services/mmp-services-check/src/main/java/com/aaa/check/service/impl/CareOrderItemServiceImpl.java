package com.aaa.check.service.impl;

import com.aaa.check.dao.CareOrderItemDao;
import com.aaa.check.service.CareOrderItemService;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开诊细表(HisCareOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2023-03-27 10:56:57
 */
@Service
public class CareOrderItemServiceImpl implements CareOrderItemService {
   @Autowired
   private CareOrderItemDao careOrderItemDao;

   @Override
   public Result<List<CareOrderItem>> selectAll(CheckItemVo checkItemVo) {
      List checkItemIds = checkItemVo.getCheckItemIds();
      String regId = checkItemVo.getRegId();
      List<CareOrderItem> careOrderItems = careOrderItemDao.selectAll(checkItemIds, regId);
      return new Result<>(200, "检查结果查询", careOrderItems);
   }
}
