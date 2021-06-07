package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * RUL: /addUser
     * @param user
     * return：success
     */
    @PostMapping("addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "success";
    }

    /**
     * 查询demo_user表的全部数据
     * 请求路径：http://localhost:8090/getUser
     * 参数： 不需要参数
     * @return List<User>
     */

    @PostMapping("getUser")
    public List<User> getAll(){
        return userService.getAll();
    }
}
