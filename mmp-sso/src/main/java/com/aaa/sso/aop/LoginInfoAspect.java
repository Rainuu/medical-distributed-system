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
        String os=getRequestSystemInfo(request);
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

    private   String getRequestSystemInfo(HttpServletRequest request){
        String systenInfo = null;
        String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0){
            systenInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0){
            systenInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows Vista";
        } else if (header.indexOf("NT 6.1") > 0){
            systenInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0){
            systenInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0){
            systenInfo = "Windows 9";
        } else if (header.indexOf("NT 5") > 0){
            systenInfo = "Windows 2000";
        } else if (header.indexOf("NT 4") > 0){
            systenInfo = "Windows NT4";
        } else if (header.indexOf("Me") > 0){
            systenInfo = "Windows Me";
        } else if (header.indexOf("98") > 0){
            systenInfo = "Windows 98";
        } else if (header.indexOf("95") > 0){
            systenInfo = "Windows 95";
        } else if (header.indexOf("Mac") > 0){
            systenInfo = "Mac";
        } else if (header.indexOf("Unix") > 0){
            systenInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0){
            systenInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0){
            systenInfo = "SunOS";
        }
        return systenInfo;
    }
}
