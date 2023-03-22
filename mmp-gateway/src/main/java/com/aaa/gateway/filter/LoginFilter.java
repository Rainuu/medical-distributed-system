package com.aaa.gateway.filter;

import com.aaa.core.util.JwtUtil;
import com.aaa.core.vo.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 11:09
 */
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.获取请求路径
        String path = request.getPath().toString();

        //3.判断该路径是否是放行的路径
        if("/login".equals(path)){
            return chain.filter(exchange);
        }

        //4.获取请求请求头的token
        String token = request.getHeaders().getFirst("token");
        System.out.println("token========"+token);
        if(StringUtils.hasText(token)&& JwtUtil.verifyToken(token)&&redisTemplate.hasKey(token)){
            //5.刷新token的时间
            redisTemplate.expire(token,30, TimeUnit.MINUTES);
            return chain.filter(exchange);
        }
        Result<String> result=new Result<>(5001,"登录失效");

        //3.JSON转换
        byte[] bytes = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        //调用bufferFactory方法,生成DataBuffer对象
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        //4.调用Mono中的just方法,返回要写给前端的JSON数据
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
