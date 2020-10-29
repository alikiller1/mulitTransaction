package com.liuqh.mulittransaction.config.web;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class InterceptorConfig implements WebMvcConfigurer {

    @Override

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new JWTInterceptor())
                .excludePathPatterns("/user/login", "/index")  //用户接口都需要放行，因为登陆和注册是还没有token，如果此时验证token则肯定不会成功
                .addPathPatterns("/**") ;       //需要token验证的接口 一般是全部接口



    }

}

