package com.liuqh.mulittransaction.config.web;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuqh.mulittransaction.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**

 * 避免每次都手动穿参数，我们直接写一个拦截器

 */

public class JWTInterceptor implements HandlerInterceptor {

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map<String, Object> map = new HashMap<>();//实际业务中应该是Result实体类

        //从请求头中获取令牌

        String token = request.getHeader("token");

        try {

            JWTUtils.verify(token);//验证令牌

            return true;//放行请求

        } catch (SignatureVerificationException e) {

            map.put("msg","无效签名!");

        }catch (TokenExpiredException e){

            map.put("msg","token过期!");

        }catch (AlgorithmMismatchException e){
            map.put("msg","token算法不一致!");
        }catch (Exception e){
            map.put("msg","token无效!!");
        }

        map.put("state",false);//设置状态为验证失败

        //前后端分离需要返回信息是json格式的 但是不在Controller中，无法自动将map转为为json

        // 所以使用jackson手动转换

        String json = new ObjectMapper().writeValueAsString(map);

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().println(json);

        return false;

    }

}

