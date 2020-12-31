package com.example.demo.framework.config;

import com.example.demo.framework.Filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new JwtFilter());
        registration.addUrlPatterns("/*");
        registration.setName("JwtFilter");
        registration.setOrder(1);
        return registration;
    }

}
