package com.li.dao;

import com.li.po.User;

public interface UserDao {
    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 用户实体类
     */
    User login(String account, String password);

    /**
     * 用户注册
     *
     * @param user 用户实体类
     * @return 是否注册成功
     */
    Boolean register(User user);

    /**
     * 用户忘记密码
     *
     * @param account 账号
     * @param phone   手机号
     * @param email   邮箱
     * @return 用户实体类
     */
    User forget(String account, String phone, String email);
}
