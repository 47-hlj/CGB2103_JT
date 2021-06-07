package com.jt.service.impl;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getUser() {

        return userMapper.selectList(null);
    }

    @Override
    public User getUserById(Integer id) {

        return userMapper.selectById(id);
    }

    @Override
    public void deleteUser(Integer id) {

        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {//id/name

        userMapper.updateById(user);
    }
}