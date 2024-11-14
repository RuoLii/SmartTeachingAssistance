package com.li.service;

import com.li.po.User;
import com.li.utils.Result;

public interface UserService {
    /**
     * 处理用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return 处理结果
     */
    Result login(String account, String password);

    /**
     * 处理用户注册
     *
     * @param user 用户 po
     * @return 处理结果
     */
    Result register(User user);

    /**
     * 处理用户忘记密码
     *
     * @param user 用户 po
     * @return 处理结果
     */
    Result forgetPassword(User user);
}
