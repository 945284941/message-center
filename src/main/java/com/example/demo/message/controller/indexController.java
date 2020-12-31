package com.example.demo.message.controller;

import com.example.demo.message.domain.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class indexController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/loginSuccess")
    @ResponseBody
    public AjaxResult loginSuccess(HttpServletRequest httpServletRequest){
         httpServletRequest.getSession().setAttribute("name","鹏");
        return AjaxResult.success("");
    }
}
