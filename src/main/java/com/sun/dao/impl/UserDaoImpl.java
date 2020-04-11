package com.sun.dao.impl;

import com.sun.bean.User;
import com.sun.dao.BaseDao;
import com.sun.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUserByUsernameAndPassword(User user) {
        String sql="select * from bs_users where username=? and password=?";
        return this.getBean(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public boolean registUser(User user) {
        String sql ="insert into bs_users(username,password,email)values(?,?,?)";
        int res=this.update(sql,user.getUsername(),user.getPassword(),user.getEmail());

        return res>0;
    }
}
