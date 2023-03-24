package com.aaa.check.service.impl;

import com.aaa.check.dao.CheckResultDao;
import com.aaa.check.service.CheckResultService;


import com.aaa.core.entity.CheckResult;
import com.aaa.core.entity.Role;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (HisCheckResult)表服务实现类
 *
 * @author makejava
 * @since 2023-03-24 09:37:26
 */
@Service
public class CheckResultServiceImpl implements CheckResultService {
   @Autowired
   private CheckResultDao checkResultDao;

   @Override
   public Result<IPage<CheckResult>> getByPage(Integer current, Integer size, CheckItemVo checkItemVo) {
      IPage<CheckResult> page = new Page<>(current,size);
      QueryWrapper<CheckResult> wrapper = new QueryWrapper<>();
      String[] checkItemIds = checkItemVo.getCheckItemIds();
      if (StringUtils.hasText(checkItemVo.getRegId())){
         wrapper.eq("reg_id",checkItemVo.getRegId());
      }
      if (StringUtils.hasText(checkItemVo.getPatientName())){
         wrapper.like("patient_name",checkItemVo.getPatientName());
      }
      for (int i = 0; i < checkItemIds.length-1; i++) {
         if (StringUtils.hasText(checkItemIds[i])){
            wrapper.eq("check_item_id",checkItemIds[i]).or();
         }
      }
      if (checkItemIds.length!=0){
         if (StringUtils.hasText(checkItemIds[checkItemIds.length-1])){
            wrapper.eq("check_item_id",checkItemIds[checkItemIds.length-1]);
         }
      }


      IPage<CheckResult> checkResultIPage = checkResultDao.selectPage(page, wrapper);
      return new Result<>(200,"检查结果查询",checkResultIPage);
   }
}
