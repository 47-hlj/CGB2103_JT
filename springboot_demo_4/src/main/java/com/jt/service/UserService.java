package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getUser();

    User getUserById(Integer id);

    void deleteUser(Integer id);

    void updateUser(User user);
}