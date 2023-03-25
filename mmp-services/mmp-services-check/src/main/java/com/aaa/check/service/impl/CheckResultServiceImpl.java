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
   public Result<IPage<CheckResult>> getByPageStatus(Integer current, Integer size, CheckItemVo checkItemVo) {
      IPage<CheckResult> page = new Page<>(current,size);
      QueryWrapper<CheckResult> queryWrapper = new QueryWrapper<>();
      String[] checkItemIds = checkItemVo.getCheckItemIds();
      if (StringUtils.hasText(checkItemVo.getStatus())){
         queryWrapper.eq("result_status",checkItemVo.getStatus());
      }
      if (StringUtils.hasText(checkItemVo.getRegId())){
         queryWrapper.eq("reg_id",checkItemVo.getRegId());
      }
      if (StringUtils.hasText(checkItemVo.getPatientName())){
         queryWrapper.like("patient_name",checkItemVo.getPatientName());
      }
      if (checkItemIds.length==4){
         queryWrapper.and(wrapper -> wrapper.eq("check_item_id",checkItemIds[0]).or().eq("check_item_id",checkItemIds[1]).or().
                 eq("check_item_id",checkItemIds[2]).or().eq("check_item_id",checkItemIds[3])
         );
      }else if (checkItemIds.length==3){
         queryWrapper.and(wrapper -> wrapper.eq("check_item_id",checkItemIds[0]).or().eq("check_item_id",checkItemIds[1]).or().
                 eq("check_item_id",checkItemIds[2])
         );
      }else if (checkItemIds.length==2){
         queryWrapper.and(wrapper -> wrapper.eq("check_item_id",checkItemIds[0]).or().eq("check_item_id",checkItemIds[1])
         );
      }else if (checkItemIds.length==1){
         queryWrapper.eq("check_item_id",checkItemIds[0]);
      }
      IPage<CheckResult> checkResultIPage = checkResultDao.selectPage(page, queryWrapper);
      return new Result<>(200,"检查结果查询",checkResultIPage);
   }

   @Override
   public Result addMsg(String cocId, String textarea) {
      Boolean aBoolean = checkResultDao.addMsg(cocId, textarea);
      return new Result(200,"录入结果成功",aBoolean);
   }
}
