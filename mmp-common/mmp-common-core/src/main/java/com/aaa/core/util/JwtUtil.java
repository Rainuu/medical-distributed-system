package com.aaa.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 10:31
 */
public class JwtUtil {
    private static String sign="MedicalManagementPlatform";

    //1.生成jwt的token方法
    public static String createJWT(Map<String,Object> map){

        //定义头信息
        Map<String,Object> head= new HashMap<>();
        head.put("alg","HS256");
        head.put("typ","JWT");

        //定义颁发时间
        Date iat=new Date();

        //过期时间
        Calendar expire = Calendar.getInstance();
        expire.set(Calendar.SECOND,24*3600);  //24*3600
        Date expireTime = expire.getTime();

        String token = JWT.create()
                //头信息
                .withHeader(head)
                //设置颁发时间
                .withIssuedAt(iat)
                //设置过期时间
                .withExpiresAt(expireTime)
                //自定义的内容
                .withClaim("userinfo",map)
                //签名
                .sign(Algorithm.HMAC256(sign));
        return token;
    }

    //2.校验token是否有效
    public static boolean verifyToken(String token){
        //获取一个JWT校验对象
        JWTVerifier build = JWT.require(Algorithm.HMAC256(sign)).build();
        try {
            //调用校验功能
            DecodedJWT verify = build.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("token无效");
            return false;
        }
    }

    //3.从token中获取相关的载荷内容
    public static Map<String,Object> getTokenChaim(String token){
        //获取一个JWT校验对象
        JWTVerifier build = JWT.require(Algorithm.HMAC256(sign)).build();
        //调用校验功能
        DecodedJWT verify = build.verify(token);
        Claim loginInfo = verify.getClaim("userinfo");
        return loginInfo.asMap();
    }

    public static void main(String[] args) {
        // 测试token使用

    }
}
