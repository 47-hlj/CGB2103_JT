package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> getAll();
}
