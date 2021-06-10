package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {

    @GetMapping("/getA")
    public String getA(Integer id){
        System.out.println("我是A请求:"+id);
        //当用户访问A时,由服务器动态的将请求发送给B
        //关键字: farword
        //return "forward:/getB";   转发
        return "redirect:/getB";    //重定向
    }

    @GetMapping("/getB")
    @ResponseBody
    public String getB(Integer id){
        System.out.println("我是B请求"+id);
        return "我是B服务器"+id;
    }

}