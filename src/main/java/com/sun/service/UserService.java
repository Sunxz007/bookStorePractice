package com.sun.service;

import com.sun.bean.User;

public interface UserService {
        /**
         * 用户登录
         * @param user 用于存储user信息的对象
         * @return 存储的登录user信息的对象
         */
        User login(User user);

        /**
         * 用户登录
         * @param user 用于存储user信息的对象
         * @return 登录结果
         */
        boolean register(User user);

    /**
     * 根据username验证用户是否存在
     * @param user 封装了username的user对象
     * @return 是否登录存在
     */
    boolean checkUser(User user);
}
