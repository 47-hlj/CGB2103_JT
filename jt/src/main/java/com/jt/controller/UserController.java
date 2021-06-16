package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
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

    /**
     * 1.业务需求: 根据条件进行分页查询
     *  请求路径: /user/list
     *  请求类型: GET
     *  请求参数: 后台使用PageResult对象接收
     *  返回值:  SysResult(pageResult对象)
     * 分析:
     *      1.用户参数3个   2.要求返回值5个
     */
    @GetMapping("/list")
    public SysResult getUserList(PageResult pageResult){

        pageResult = userService.getUserList(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     * URL地址: /user/status/{id}/{status}
     * 参数:  1.id   2.status
     * 返回值: SysResult对象
     *
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(User user){
        userService.updateStatus(user);
        return SysResult.success();
    }

    /**
     * URL地址: /user/{id}
     * 参数: id
     * 返回值: SysResult对象
     */
    @DeleteMapping("/{id}")
    public SysResult deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return SysResult.success();
    }
}
