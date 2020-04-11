package com.sun.dao;


import com.sun.bean.User;

/**
 * @author sun
 */
public interface UserDao{
    /**
     *
     * 按照用户名密码查询详细信息
     * @param user
     * @return boolean
     */
    public User getUserByUsernameAndPassword(User user);

    /**
     * 注册一个user
     * @param user
     * @return boolean
     */
    public boolean registUser(User user);
}
