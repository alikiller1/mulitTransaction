package com.liuqh.mulittransaction.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.liuqh.mulittransaction.controller.vo.User;
import com.liuqh.mulittransaction.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    /**

     * 生成token 返回给前段 自动存储在header中

     */

    @GetMapping("/user/login")
    public Map<String,Object> login(User user){

        log.info("用户名: [{}]",user.getName());

        log.info("密码: [{}]",user.getPassword());

        Map<String, Object> map = new HashMap<>(); // 实际业务中会使用一个Result实体类

        try{
            if("liuqh".equals(user.getName())&&"123".equals(user.getPassword())){
                log.info("用户登录，校验成功");
            }else{
                throw new Exception("用户名或密码错误");
            }
            Map<String,String> payload =  new HashMap<>(); //存储用户信息用于生成token
            payload.put("name",user.getName());

            String token = JWTUtils.getToken(payload); // 生成JWT的令牌

            map.put("state",true);

            map.put("msg","认证成功");

            map.put("token",token);//响应token

        }catch (Exception e){

            map.put("state",false);

            map.put("msg",e.getMessage());

        }

        return map;

    }

    /**

     * 业务需要使用token中的用户信息是，通过request参数获得token

     */

    @PostMapping("/user/test")
    public Map<String,Object> test(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>(); // 实际业务中会使用Result实体类

        /* 自己的业务逻辑 */

        String token = request.getHeader("token"); // 获取token

        // 验证token 必然是成功的，不然是无法进入这个路由的，这步的主要目的是获取DecodedJWT对象

        // 这步完全可以替换成 DecodedJWT verify = JWTUtils.decode(token);

        DecodedJWT verify = JWTUtils.verify(token);
        log.info("用户name: [{}]",verify.getClaim("name").asString());
        map.put("state",true);
        map.put("msg","请求成功!");

        return map;
    }

    @GetMapping("index")
    public String index(){
        return "server is running";
    }

}

