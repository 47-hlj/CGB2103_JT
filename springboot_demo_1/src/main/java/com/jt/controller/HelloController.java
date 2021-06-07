package com.jt.controller;

import com.jt.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //向前端返回JSON 特殊格式的字符串.
//spring容器加载指定的配置文件
@PropertySource(value = "classpath:/properties/hello.properties",encoding = "utf-8")
public class HelloController {

    //需求：从容器中获取属性数据 springel=spel表达式
    @Value("${hello.msg}") //自动生成get/set方法
    private String msg;//= "您好SpringBoot";  从yml文件获取数据

    @Value("${jt.msg}")
    private String msg2;//从pro文件获取数据

    //如果返回值为string类型,则按照原始格式返回
    @GetMapping("/hello")
    public String hello(){
        return msg+"|"+msg2;
    }
}
