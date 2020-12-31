package com.example.demo.framework.config;

import com.example.demo.framework.Interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
     public void addInterceptors(InterceptorRegistry registry){
         registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/message/**");
     }


}
