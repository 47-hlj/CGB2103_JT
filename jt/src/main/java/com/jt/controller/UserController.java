package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 需求: 根据用户名和密码实现用户登录,要求返回token
     * URL: /user/login
     * 请求参数: post  JSON {username:"xxx",password:"xxxx"}
     * 返回值: SysResult对象 (token)
     */

    @PostMapping("/login")
    public SysResult login(@RequestBody User user){
        //执行后端登录操作，要求返回token密钥
        String token = userService.login(user);
        //判断token返回值是否有效
        if(StringUtils.hasLength(token)){
            return SysResult.success(token);
        } else
        {
          //登录失败
          return SysResult.fail();
        }

    }


}
