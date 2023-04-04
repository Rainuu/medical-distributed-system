package com.aaa.doctor.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aaa.core.entity.*;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.CareHistoryDao;
import com.aaa.doctor.dao.CareOrderDao;
import com.aaa.doctor.dao.CareOrderItemDao;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.util.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 10:20
 */
@Service
public class CareHistoryServiceImpl implements CareHistoryService {

    @Autowired
    private CareHistoryDao careHistoryDao;

    @Autowired
    private CareOrderItemDao careOrderItemDao;

    @Autowired
    private CareOrderDao careOrderDao;

    @Autowired
    private UserFeign userFeign;

    @Override
    public Result<List<CareHistory>> queryCareHistoryByPatientId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
      List<CareHistory> historyList =  careHistoryDao.selectAll(patientId);

        return new Result(2000,"查询患者病历成功",historyList);
    }

    @Override
    public List<CareHistory> getCareHistoryId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
        List<CareHistory> careHistoryList = careHistoryDao.selectList(wrapper);
        return careHistoryList;
    }




    /**
     * 保存病历
     */
    @Override
    public Result<CareHistory> SaveHistroy(String registrationId, CareHistory careHistory) {
        HttpServletRequest request = WebUtil.getRequest();
        String phone = (String) JwtUtil.getTokenChaim(request.getHeader("token")).get("username");
        User byUsername = userFeign.getByUsername(phone);//获取当前登录的人的信息
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("registrationId",registrationId);
        IdWorker worker = new IdWorker();
        long chId = worker.nextId();
        String str = "CH"+chId;
        careHistory.setChId(str);
        careHistory.setUserId(byUsername.getUserId());
        careHistory.setUserName(byUsername.getUserName());
        careHistoryDao.insert(careHistory);
        CareHistory careHistory1 = careHistoryDao.selectById(careHistory);
        return new Result<CareHistory>(2000,"添加成功",careHistory1);
    }

//    @Override
//    public String insertCareHistory(CareHistory careHistory) {
//        String id=null;
//        if (Objects.nonNull(careHistory.getChId())){
//            String token = WebUtil.getRequest().getHeader("token");
//            Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
//            String phone = (String) tokenData.get("username");
//            User user = userFeign.getByUsername(phone);//获取当前登录的人的信息
//            Dept deptByDeptId = userFeign.getDeptByDeptId(user.getDeptId());
//
//
//            DateTime date = DateUtil.date();
//            String date1 = DateUtil.format(date, "yyyy-MM-dd");
//            careHistory.setUserId(user.getUserId());
//            careHistory.setUserName(user.getUserName());
//            careHistory.setDeptId(user.getDeptId());
//            careHistory.setDeptName(deptByDeptId.getDeptName());
//            careHistory.setCareTime(new Date());
//            careHistory.setCaseDate(date1);
//            int insert = careHistoryDao.insert(careHistory);
//        }else {
//            IdWorker idWorker = new IdWorker();
//            id = "CH" + idWorker.nextId();
//        }
//
//        return id;
//    }

    @Override
    public String insertCareHistory(CareHistory careHistory) {
        String id = null;
        if (Objects.nonNull(careHistory.getChId())) {
            QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
            wrapper.eq("ch_id", careHistory.getChId());
            List<CareHistory> careHistories = careHistoryDao.selectList(wrapper);
            if (careHistories.size() > 0) {
                String token = WebUtil.getRequest().getHeader("token");
                Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
                String phone = (String) tokenData.get("username");
                User user = userFeign.getByUsername(phone);//获取当前登录的人的信息
                Dept deptByDeptId = userFeign.getDeptByDeptId(user.getDeptId());

                DateTime date = DateUtil.date();
                String date1 = DateUtil.format(date, "yyyy-MM-dd");
                QueryWrapper<CareHistory> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("ch_id", careHistory.getChId());
                careHistory.setUserId(user.getUserId());
                careHistory.setUserName(user.getUserName());
                careHistory.setDeptId(user.getDeptId());
                careHistory.setDeptName(deptByDeptId.getDeptName());
                careHistory.setCareTime(new Date());
                careHistory.setCaseDate(date1);
                System.out.println("------------------");
                System.out.println(careHistory);
                int insert = careHistoryDao.update(careHistory, wrapper1);
                return insert > 0 ? "修改成功" : "修改失败";
            } else {
                String token = WebUtil.getRequest().getHeader("token");
                Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
                String phone = (String) tokenData.get("username");
                User user = userFeign.getByUsername(phone);//获取当前登录的人的信息
                Dept deptByDeptId = userFeign.getDeptByDeptId(user.getDeptId());


                DateTime date = DateUtil.date();
                String date1 = DateUtil.format(date, "yyyy-MM-dd");
                careHistory.setUserId(user.getUserId());
                careHistory.setUserName(user.getUserName());
                careHistory.setDeptId(user.getDeptId());
                careHistory.setDeptName(deptByDeptId.getDeptName());
                careHistory.setCareTime(new Date());
                careHistory.setCaseDate(date1);
                int insert = careHistoryDao.insert(careHistory);
                return insert > 0 ? "添加成功" : "添加失败";
            }

        } else {
            IdWorker idWorker = new IdWorker();
            id = "CH" + idWorker.nextId();
            return id;
        }
    }



//    @Override
//    public CareHistory queryCareHistoryId(String patientId) {
//        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
//        if (StringUtils.hasText(patientId)){
//            wrapper.eq("patient_id",patientId);
//            wrapper.orderByDesc("care_time");
//        }
//        CareHistory careHistory = careHistoryDao.selectList(wrapper).get(0);
//        QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
//        if (StringUtils.hasText(careHistory.getChId())){
//            wrapper1.eq("ch_id",careHistory.getChId());
//            List<CareOrder> careOrders = careOrderDao.selectList(wrapper1);
//            careHistory.setCareOrderList(careOrders);
//            for (CareOrder careOrder : careOrders) {
//                QueryWrapper<CareOrderItem> careOrderItemQueryWrapper = new QueryWrapper<>();
//                if (StringUtils.hasText(careOrder.getCoId())){
//                    careOrderItemQueryWrapper.eq("co_id",careOrder.getCoId());
//                    List<CareOrderItem> careOrderItems = careOrderItemDao.selectList(careOrderItemQueryWrapper);
//                    careOrder.setCareOrderItemList(careOrderItems);
//                }
//            }
//        }
//        return careHistory;
//
//    }
//新开就诊就诊完成载入按钮时 查询挂号单号的病历以就诊时间降序排获取第一条
@Override
public CareHistory queryCareHistoryId(String registrationId) {
    QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
    if (StringUtils.hasText(registrationId)){
        wrapper.eq("reg_id",registrationId);
        wrapper.orderByDesc("care_time");
    }
    List<CareHistory> careHistories = careHistoryDao.selectList(wrapper);
    if (careHistories.size()>0){
        CareHistory careHistory = careHistories.get(0);
        QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
        if (StringUtils.hasText(careHistory.getChId())){
            wrapper1.eq("ch_id",careHistory.getChId());
            List<CareOrder> careOrders = careOrderDao.selectList(wrapper1);
            careHistory.setCareOrderList(careOrders);
            for (CareOrder careOrder : careOrders) {
                QueryWrapper<CareOrderItem> careOrderItemQueryWrapper = new QueryWrapper<>();
                if (StringUtils.hasText(careOrder.getCoId())){
                    careOrderItemQueryWrapper.eq("co_id",careOrder.getCoId());
                    List<CareOrderItem> careOrderItems = careOrderItemDao.selectList(careOrderItemQueryWrapper);
                    careOrder.setCareOrderItemList(careOrderItems);
                }
            }
        }
        return careHistory;
    }
    return new CareHistory();


}

    /**
     * 根据id删除未支付的订单
     * @param itemId
     * @param amount
     * @return
     */
    @Override
    public boolean deleteCareOrderItemById(String itemId, BigDecimal amount) {
        QueryWrapper<CareOrderItem> wrapper=new QueryWrapper<>();
        wrapper.eq("item_id",itemId);
        //通过开诊明细表id查询药品内容
        CareOrderItem careOrderItem = careOrderItemDao.selectOne(wrapper);
        QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("co_id",careOrderItem.getCoId());
        //通过处方id查询处方内容
        CareOrder careOrder = careOrderDao.selectOne(wrapper1);
        //把减去药品金额后的总额储存
        careOrder.setAllAmount(careOrder.getAllAmount().subtract(amount));//减去删除的金额
        //定义一个i
        int i=0;
        //获取总额转成double类型如果大于0 执行修改 小于0 执行删除
        if (careOrder.getAllAmount().doubleValue()>0){
            //修改处方里的金额
            i = careOrderDao.updateById(careOrder);
            //删除开诊明细表里面的药品
            i = careOrderItemDao.deleteById(itemId);
        }else {
            //删除处方表
            i = careOrderDao.deleteById(careOrder.getCoId());
            //删除开诊明细表里面的药品
            i = careOrderItemDao.deleteById(itemId);
        }
        //如果i3大于0就是删除或修改成功
        return i>0?true:false;


    }

    //根据前端传输过来的病历id查看病历是否存在 存在true 不存在 false
    @Override
    public Boolean queryCareHistoryById(String chId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(chId)){
            wrapper.eq("ch_id",chId);
            List<CareHistory> careHistories = careHistoryDao.selectList(wrapper);
            return careHistories.size()>0?true:false;
        }
        return false;
    }


}
