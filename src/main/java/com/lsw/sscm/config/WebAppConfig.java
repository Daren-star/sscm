package com.lsw.sscm.config;

import com.lsw.sscm.interceptor.AdminInterceptor;
import com.lsw.sscm.interceptor.AuthHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
 
    @Autowired
    AuthHandlerInterceptor authHandlerInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor) //注册token拦截器
                .addPathPatterns("/sscm/**");// 增加拦截路径
        registry.addInterceptor(adminInterceptor) //注册管理员拦截器
                .addPathPatterns("/sscm/admin/*");
    }
}