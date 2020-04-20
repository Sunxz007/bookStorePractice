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

    /**
     * 根据username 获取信息
     *
     * @param user 存储用户名的封装对象
     * @return 用户信息
     */
    @Override
    public User getUserByUserName(User user) {
        String sql="select * from bs_users where username=?";
        return this.getBean(sql,user.getUsername());
    }
}
