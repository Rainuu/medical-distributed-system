package com.aaa.check.service.impl;

import com.aaa.check.dao.CheckResultDao;
import com.aaa.check.service.CheckResultService;


import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
      IPage<CheckResult> checkResultIPage = checkResultDao.selectAll(page,checkItemVo);
      return new Result<>(200,"检查结果查询",checkResultIPage);
   }

   @Override
   public Result addMsg(String cocId, String textarea,String name,String url) {
      Boolean aBoolean = checkResultDao.addMsg(cocId,textarea,url);
      return new Result(200,"录入结果成功",aBoolean);
   }

   @Override
   public Result addAll(CheckResult checkResult) {
      checkResult.setCreateTime(new Date());
      int insert = checkResultDao.insert(checkResult);
      return new Result(200,"开始检查成功",insert);
   }

   @Override
   public Result<List<CheckResult>> getByPage(CheckResultVo checkResultVo) {
      QueryWrapper<CheckResult> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(checkResultVo.getCheckItemName())){
         wrapper.like("check_item_id",checkResultVo.getCheckItemName());
      }
      if (StringUtils.hasText(checkResultVo.getPatientName())){
         wrapper.like("patient_name",checkResultVo.getPatientName());
      }
      if(Objects.nonNull(checkResultVo.getDateRange())&&checkResultVo.getDateRange().length==2){
         wrapper.between("create_time",checkResultVo.getDateRange()[0],checkResultVo.getDateRange()[1]);
      }
      List<CheckResult> checkResults = checkResultDao.selectList(wrapper);
      return new Result<List<CheckResult>>(2000,"查询角色",checkResults);
   }

   @Override
   public Result<List<CheckResult>> getByPageTwo(CheckResultVo checkResultVo) {
      List<CheckResult> checkResults = checkResultDao.selectAllTwo(checkResultVo);
      return new Result<List<CheckResult>>(2000,"查询角色",checkResults);
   }
}
