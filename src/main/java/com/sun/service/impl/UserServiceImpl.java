package com.sun.service.impl;

import com.sun.bean.User;
import com.sun.dao.UserDao;
import com.sun.dao.impl.UserDaoImpl;
import com.sun.service.UserService;

/**
 * @author sun
 */
public class UserServiceImpl implements UserService {
    private UserDao ud=new UserDaoImpl();
    @Override
    public User login(User user) {
        return ud.getUserByUsernameAndPassword(user);
    }

    @Override
    public boolean register(User user) {
        return ud.registUser(user);
    }
}
