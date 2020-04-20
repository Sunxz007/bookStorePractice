package com.sun.dao;


import com.sun.bean.User;

/**
 * @author sun
 */
public interface UserDao{
    /**
     *
     * 按照用户名密码查询详细信息
     * @param user 用于存储user信息的对象
     * @return boolean
     */
    User getUserByUsernameAndPassword(User user);

    /**
     * 注册一个user
     * @param user 用于存储user信息的对象
     * @return boolean
     */
    boolean registUser(User user);

    /**
     * 根据username 获取信息
     * @param user 封装了username的user对象
     * @return 用户信息
     */
    User getUserByUserName(User user);
}
