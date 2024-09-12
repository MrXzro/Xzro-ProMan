package com.xzro.config;

import com.xzro.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MainConfig
 * Package: com.xzro.config
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/25 15:53
 * @Version 1.0
 */
@Configuration
public class MainConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
    }
}
