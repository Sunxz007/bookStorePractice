package com.sun.service;

import com.sun.bean.User;

public interface UserService {

    public User login(User user);
    public boolean register(User user);
}
