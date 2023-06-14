package com.aaa.sso.config;


import com.aaa.core.util.JwtUtil;
import com.aaa.core.vo.Result;
import com.aaa.sso.aop.annotation.LoginLog;
import com.aaa.sso.feign.UserFeign;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserFeign userFeign;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
//    @LoginLog
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .loginProcessingUrl("/login").permitAll();

        http.cors();
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
    }

    //认证失败是处理的功能，失败时响应的json数据
    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                Result<String> result = new Result<>(500, "登陆失败");

                String jsonString = JSON.toJSONString(result);

                //把json响应给前端
                writer.println(jsonString);

                //关闭流、刷新流
                writer.flush();
                writer.close();
            }
        };
    }

    //认证成功是处理的功能，成功时响应的json数据
    private AuthenticationSuccessHandler successHandler() {

        return new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                System.out.println("====== 成功 ======");
                response.setContentType("application/json;charset=utf-8"); //防止编码问题，json乱码
                PrintWriter writer = response.getWriter();
                User principal = (User) authentication.getPrincipal();  //拿到整个登陆成功的用户信息
                String username = principal.getUsername();//拿到用户名
                Collection<GrantedAuthority> authorities = principal.getAuthorities();//获取当前用户的权限
                List<String> collect = authorities.stream().map(item -> item.getAuthority()).collect(Collectors.toList());
                com.aaa.core.entity.User byUsername = userFeign.getByUsername(username);
                //把用户权限和权限防撞到map对象中，为了token使用
                Map<String, Object> map = new HashMap<>();
                map.put("username2", byUsername.getUserName());
                map.put("username", username);  //用户名
                map.put("authorities", collect);  //权限集合

                //生成token
                String token = JwtUtil.createJWT(map);

                //如何刷新过期时间，一旦登录后计时刷新令牌过期时间，而不是重新生成令牌会导致令牌不对照--使用redis
                redisTemplate.opsForValue().set(token, JSON.toJSONString(map), 30, TimeUnit.MINUTES);
                //方便刷新token令牌的过期时间


                //前后端分离，把token响应给前端，响应一个公共实体类统一格式Result,把token放进result中统一格式发给前端
                Result<String> result = new Result<>(200, "登陆成功", token);
                //把对象转为json字符串，使用阿里的fastjson,需要导包
                String jsonString = JSON.toJSONString(result);
                //把json响应给前端
                writer.println(jsonString);
                //关闭流、刷新流
                writer.flush();
                writer.close();

            }
        };
    }
}
