package com.aaa.doctor.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aaa.core.entity.Patient;
import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.doctor.dao.PatientDao;
import com.aaa.doctor.dao.RegistrationDao;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.RegistrationService;
import com.aaa.doctor.util.IdWorker;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 22:41
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private UserFeign userFeign;


    /*
     * 查询新开就诊中的就诊列表状态
     * */
    @Override
    public List<Registration> queryRegistrationStatus1(String status) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            wrapper.eq("registration_status",status);
        }
        List<Registration> registrations = registrationDao.selectList(wrapper);
        return registrations;
    }

    /**
     * 给门诊挂号添加挂号单号
     * @param userId
     * @param regItemAmount
     * @param regItemId
     * @param obj
     * @return
     */
    @Override
    public Boolean insertRegistration(String userId, String regItemAmount, Long regItemId, Map<String, Object> obj) {
        Integer insert=0;
        //id随机生成
        String id="";
        //生成id
        IdWorker idWorker = new IdWorker();
        Object tableData = obj.get("tableData");
        String tableData1 = JSON.toJSONString(tableData);
        Scheduling scheduling = JSON.parseObject(tableData1, Scheduling.class);

        Object patientParams = obj.get("patientParams");
        String patientParams1 = JSON.toJSONString(patientParams);
        Patient patient = JSON.parseObject(patientParams1, Patient.class);
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(patient.getIdCard())){
            wrapper.eq("id_card",patient.getIdCard());
        }
        List<Patient> patients = patientDao.selectList(wrapper);
        System.out.println("----------------");
        System.out.println(patients.size());
        if (patients.size()>0){
            String token = WebUtil.getRequest().getHeader("token");
            Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
            String phone = (String) tokenData.get("username");
            User user = userFeign.getByUsername(phone);
            User userByUserId = userFeign.queryById(Long.valueOf(userId));
            DateTime date = DateUtil.date();
            String visitDate = DateUtil.format(date, "yyyy-MM-dd");
            Integer regNumber = registrationDao.getRegNumber(scheduling.getDeptId());
            System.out.println(scheduling.getDeptId());
            System.out.println(Objects.nonNull(regNumber));
            System.out.println(regNumber);
            if (Objects.nonNull(regNumber)){
                regNumber=regNumber+1;
            }else {
                regNumber=1;
            }

            Registration registration = new Registration();
            id= "GH"+idWorker.nextId();
            registration.setRegistrationId(id);//挂号流水
            registration.setPatientId(patient.getPatientId());//患者id
            registration.setPatientName(patient.getName());//患者姓名
            registration.setUserId(scheduling.getUserId());//接诊医生id
            registration.setDoctorName(userByUserId.getUserName());//接诊医生id
            registration.setDeptId(scheduling.getDeptId());//科室id
            registration.setRegistrationItemId(regItemId);//挂号费用id
            registration.setRegistrationAmount(new BigDecimal(regItemAmount));//挂号总金额 挂号费
            registration.setRegistrationNumber(regNumber);//挂号编号
            registration.setRegistrationStatus("1");//挂号状态 1待接诊
            registration.setVisitDate(visitDate);//就诊日期
            registration.setSchedulingType(scheduling.getSchedulingType());//排班类型 门诊 急诊等
            registration.setSubsectionType(scheduling.getSubsectionType());//排班时段 上午 下午
            registration.setCreateTime(new Date()); //创建时间
            registration.setUpdateTime(new Date());//更新时间
            registration.setCreateBy(user.getUserName());//创建人

            insert = registrationDao.insert(registration);
        }else {
            id="HZ"+idWorker.nextId();
            patient.setPatientId(id);
            patient.setCreateTime(new Date());
            insert = patientDao.insert(patient);
        }

        return insert>0?true:false;
    }

    // 根据部门id查询最大编号
    @Override
    public Integer getRagNumber(Long deptId) {
        Integer regNumber = registrationDao.getRegNumber(deptId);
        if (!Objects.nonNull(regNumber)) {
            regNumber=0;
        }
        return regNumber;
    }
}
