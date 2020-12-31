package com.example.demo.framework.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AdminInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        try {


            if(httpServletRequest.getSession().getAttribute("name")!=null){
                return true;
            }
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }


}
