package com.liuqh.mulittransaction.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**

 * 将JWT封装成工具类

 */

@SuppressWarnings("unused")
public class JWTUtils {

    // 设置sign（密钥）

    public static final String SIGN = "qweasd";

    /**

     * 生成token 形式为：header.payload.sign

     */

    public static String getToken(Map<String, String> map) {

        // 过期时间为七天

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.SECOND, 20);

        // 创建JWT builder

        JWTCreator.Builder builder = JWT.create();

        // 存入信息payload中信息（也就是map中的信息）

        map.forEach((k, v) -> builder.withClaim(k, v));

        // 存入过期时间和密钥

        String token = builder.withExpiresAt(instance.getTime())

                .sign(Algorithm.HMAC256(SIGN));

        return token;

    }

    /**

     * 验证token 如果正确则返回token信息

     */

    public static DecodedJWT verify(String token) {

        // 如果token不对会直接抛出异常

        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

    }

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("userId","1");
        map.put("userName","liuqh");
        String token=getToken(map);
        System.out.println(token);
        DecodedJWT d=verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImxpdXFoIiwiZXhwIjoxNjAzOTM0Nzg2LCJ1c2VySWQiOiIxIn0.bkPt_ruEPojbdGfugs7NdulCSlHG-2mqPtdU6p2eaog");
        System.out.println(d);
    }

}
