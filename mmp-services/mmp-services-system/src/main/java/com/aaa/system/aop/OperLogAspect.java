package com.aaa.system.aop;

import com.aaa.core.entity.OperLog;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.system.aop.annotation.Log;
import com.aaa.system.dao.OpenLogDao;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class OperLogAspect {
    @Autowired
    private OpenLogDao openLogDao;

    //定义切点
    @Pointcut(value = "@annotation(com.aaa.system.aop.annotation.Log)")
    private void pointcut() {

    }

    //定义通知
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        String title = log.title();
        String businessType = log.business_type();
        String name = method.getName();
        HttpServletRequest request = WebUtil.getRequest();
        String requestMethod = request.getMethod();
        String operName = (String) JwtUtil.getTokenChaim(request.getHeader("token")).get("username");
        String oper_url = request.getServletPath();
        String remoteAddr = request.getRemoteAddr();
        String oper_location = remoteAddr.startsWith("192.168.") ? "内网IP" : "外网IP";
        String oper_param = JSON.toJSONString(joinPoint.getArgs());
        String json_result = JSON.toJSONString(result);
        String status = "0";
        Date oper_time = new Date();
        OperLog operLog = new OperLog(null, title, businessType, name, requestMethod, log.operator_type(), operName, oper_url, remoteAddr, oper_location, oper_param, json_result, status, "", oper_time);
        openLogDao.insert(operLog);
    }
}
