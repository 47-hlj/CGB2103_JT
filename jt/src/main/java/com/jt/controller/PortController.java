package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PortController {

    /**
     * 需求：要求通过getPort方法，动态获取当前端口号信息
     */

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public String getPort(){
        return "当前服务器的端口号："+port;
    }
}
