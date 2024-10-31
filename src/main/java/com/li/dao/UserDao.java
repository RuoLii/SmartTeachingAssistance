package com.li.dao;

import com.li.po.User;

public interface UserDao {
    User login(String account, String password);

    Boolean register(User user);

    User forget(String account, String phone, String email);
}
