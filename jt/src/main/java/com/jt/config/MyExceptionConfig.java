package com.jt.config;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//问题1: 全局异常的处理 应该拦截哪一层的代码?? mapper,service,controller????
//@ControllerAdvice + @ResponseBody
@RestControllerAdvice //全局异常处理 返回值为JSON
public class MyExceptionConfig {

    //问题2: 什么时候调用? 有异常的时候调用
    //数组的写法
    //@ExceptionHandler({RuntimeException.class, SqlSessionException.class})
    @ExceptionHandler({RuntimeException.class})
    public Object handler(Exception exception){
        //将报错信息控制台打印
        exception.printStackTrace();
        return SysResult.fail();
    }
}

