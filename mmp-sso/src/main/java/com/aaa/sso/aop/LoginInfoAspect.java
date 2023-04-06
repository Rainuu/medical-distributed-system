package com.aaa.sso.aop;

import com.aaa.core.entity.LoginInfo;
import com.aaa.core.entity.User;
import com.aaa.core.util.IpUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.sso.feign.LoginLogFeign;
import com.aaa.sso.feign.UserFeign;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LoginInfoAspect {
    @Autowired
    private LoginLogFeign loginLogFeign;
    @Autowired
    private UserFeign userFeign;
    @Pointcut(value = "@annotation(com.aaa.sso.aop.annotation.LoginLog)")
    private void pointcut(){}
    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        System.out.println("===============登录日志开始=====================");
        HttpServletRequest request = WebUtil.getRequest();
        String loginAccount = request.getParameter("username");
        User user = userFeign.getByUsername(loginAccount);
        String username=user.getUserName();
        String ipAddr = request.getRemoteAddr();
        String LoginLocation="";
        if (ipAddr.startsWith("192.168.")){
            LoginLocation="内网";
        }else {
            LoginLocation = IpUtil.getAddress(ipAddr);
        }
        String browser=getBrowserName(request.getHeader("User-Agent"));
        String os=getRequestSystemInfo(request.getHeader("User-Agent"));
        String loginStatus="0";
        String loginType="0";
        String msg="";
        Date LoginTime=new Date();
        LoginInfo loginInfo=new LoginInfo(null,username,loginAccount,ipAddr,LoginLocation,browser,os,loginStatus,loginType,msg,LoginTime);
        loginLogFeign.addLoginLog(loginInfo);
        System.out.println("===============登录日志结束=====================");

    }

    private String getBrowserName(String agent)
    {
        if(StringUtils.isEmpty(agent))
        {
            return "unknown";
        }
        agent = agent.toLowerCase();

        if(agent.indexOf("msie 7")>0){

            return "ie7";

        } else if(agent.indexOf("msie 8")>0){

            return "ie8";

        } else if(agent.indexOf("msie 9")>0){

            return "ie9";

        } else if(agent.indexOf("msie 10")>0){

            return "ie10";

        } else if(agent.indexOf("msie")>0){

            return "ie";

        } else if(agent.indexOf("chrome")>0){

            return "chrome";

        } else if(agent.indexOf("opera")>0){

            return "opera";

        } else if(agent.indexOf("firefox")>0){

            return "firefox";

        } else if(agent.indexOf("webkit")>0){

            return "webkit";

        } else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){

            return "ie11";

        } else{

            return "Others";
        }
    }

    private   String getRequestSystemInfo(String header){
        String systenInfo = "";
        if (header == null || header.equals(""))// 为空都默认win10
        {
            systenInfo = "windows10";
            return systenInfo;
        }

        // 得到用户的操作系统
        if (header.indexOf("NT 6.1") > 0 || header.indexOf("NT 5") > 0 || header.indexOf("NT 6.3") > 0 || header.indexOf("NT 6.2") > 0 || header.indexOf("NT 6.0") > 0 || header.indexOf("NT 5.1") > 0
                || header.indexOf("NT 5.2") > 0 || header.indexOf("NT 6.0") > 0)// win10一下的都取win7
        {
            systenInfo = "windows7";
        }
        if (header.indexOf("Mac") > 0)// mac系统
        {
            systenInfo = "mac系统";
        }
        if (header.indexOf("Unix") > 0)// unix系统
        {
            systenInfo = "unix系统";
        }
        if (header.indexOf("SunOS") > 0)// solaris系统
        {
            systenInfo = "solaris系统";
        }
        if (header.indexOf("Linux") > 0)// Linux系统
        {
            systenInfo = "linux系统";
        }
        if (header.indexOf("Ubuntu") > 0)// ubuntu系统
        {
            systenInfo = "ubuntu系统";
        }
        if (header.indexOf("iPhone") > 0)// 苹果手机
        {
            systenInfo = "苹果手机";
        }
        if (header.indexOf("Android") > 0)// 安卓系统
        {
            systenInfo = "安卓手机";
        }
        if (header.indexOf("NT 10") > 0)// win10
        {
            systenInfo = "windows10";
        }
        if (header == null || header.equals("") || systenInfo.equals("") || systenInfo == null)// 没找到默认为windows10
        {
            systenInfo = "windows10";
        }
        return systenInfo;
    }
}
