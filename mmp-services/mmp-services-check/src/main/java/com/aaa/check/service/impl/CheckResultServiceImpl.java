package com.aaa.check.service.impl;

import com.aaa.check.dao.CareOrderItemDao;
import com.aaa.check.feign.CheckResultFeign;
import com.aaa.check.dao.CheckResultDao;
import com.aaa.check.service.CheckResultService;


import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

   @Autowired
   private CheckResultFeign checkResultFeign;

   @Autowired
   private CareOrderItemDao careOrderItemDao;

   @Override
   public Result<IPage<CheckResult>> getByPageStatus(Integer current, Integer size, CheckItemVo checkItemVo) {
      IPage<CheckResult> page = new Page<>(current,size);
      IPage<CheckResult> checkResultIPage = checkResultDao.selectAll(page,checkItemVo);
      return new Result<>(200,"检查结果查询",checkResultIPage);
   }

   @Override
   public Result addMsg(String cocId, String textarea,String name,String url) {
      String itemId= cocId;
      Boolean aBoolean1 = careOrderItemDao.updateStatus(itemId);
      System.out.println("aBoolean1 = " + aBoolean1);
      Boolean aBoolean = checkResultDao.addMsg(cocId,textarea,url);
      return new Result(200,"录入结果成功",aBoolean);
   }

   @Override
   public Result addAll(CheckResult checkResult) {
      checkResult.setCreateTime(new Date());
      HttpServletRequest request = WebUtil.getRequest();
      String token = request.getHeader("token");
      Map<String, Object> info = JwtUtil.getTokenChaim(token);
      String phone = (String) info.get("username");
      User byUsername = checkResultFeign.getByUsername(phone);
      String userName = byUsername.getUserName();
      checkResult.setCreateBy(userName);
      int insert = checkResultDao.insert(checkResult);
      return new Result(200,"开始检查成功",insert);
   }

   @Override
   public Result<List<CheckResult>> getByPage(String checkItemId,String patientName, String dateRange1,
                                              String dateRange2) {
      System.out.println("====================================="+checkItemId);
      QueryWrapper<CheckResult> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(checkItemId)){
         wrapper.like("check_item_id",checkItemId);
      }
      if (StringUtils.hasText(patientName)){
         wrapper.like("patient_name",patientName);
      }
      if(StringUtils.hasText(dateRange1)&&StringUtils.hasText(dateRange2)){
         wrapper.between("create_time",dateRange1,dateRange2);
      }
      List<CheckResult> checkResults = checkResultDao.selectList(wrapper);
      return new Result<>(2000,"查询角色",checkResults);
   }

   @Override
   public Result<List<CheckResult>> getByPageTwo(String checkItemId,String dateRange1, String dateRange2) {
      List<CheckResult> checkResults = checkResultDao.selectAllTwo(checkItemId,dateRange1,dateRange2);
      return new Result<>(2000,"查询角色",checkResults);
   }
}
