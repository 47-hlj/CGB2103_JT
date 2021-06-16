package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author 刘昱江
 * 时间 2021/5/11
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    /**
     * 实现用户登录操作
     * 1.将明文转化为密文 MD5加密
     * 2.通过username和password查询数据库.
     * 3.
     *     有数据: 用户名和密码正确  返回UUID token
     *     没有数据: 用户名和密码错误 返回null
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        String password = user.getPassword();
        //md5hash相对于md5更加"安全" 没有绝对安全的系统!!!
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Pass);
        //根据其中不为null的数据当做where条件 u/p
        //Sql: select * from user where u=#{u} and p=#{p}
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User userDB = userMapper.selectOne(queryWrapper);
        if(userDB == null){
            //没有查询到数据
            return null;
        }
        String token = UUID.randomUUID().toString().replace("-","");
        return token;

    }
}
