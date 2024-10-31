package com.li.service.impl;

import com.li.dao.UserDao;
import com.li.dao.impl.UserDaoImpl;
import com.li.po.User;
import com.li.service.UserService;
import com.li.utils.AccountGenerator;
import com.li.utils.Result;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public Result login(String account, String password) {
        User user = userDao.login(account, password);
        if (user != null) {
            return new Result(true, "", user);
        }
        return new Result(false, "账号或密码有误，请重新登录！", null);
    }

    @Override
    public Result register(User user) {
        if (user.getPassword().length() < 6 || user.getPassword().length() > 10) {
            return new Result(false, "密码要求由大写字母，小写字母，数字组成，长度 6 ~ 10 ！请重新输入！", null);
        }
        if (!Pattern.matches("^(?:\\+86)?1[3-9]\\d{9}$", user.getPhone())) {
            return new Result(false, "手机号输入有误！", null);
        }
        if (!Objects.equals(user.getPassword(), user.getRePassword())) {
            return new Result(false, "两次输入密码不一致！", null);
        }
        if (!Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$", user.getEmail())) {
            return new Result(false, "邮箱输入有误！", null);
        }
        user.setAccount(AccountGenerator.generateUniqueAccount());
        Boolean register = userDao.register(user);
        if (register) {
            System.out.println("注册成功！\n账号: " + user.getAccount() + "\n密码: " + user.getPassword());
        }

        return new Result(true, "", user);
    }

    @Override
    public Result forgetPassword(User user) {
        User findUser = userDao.forget(user.getAccount(), user.getPhone(), user.getEmail());
        if (findUser != null) {
            return new Result(true, "找回成功！\n您的密码为：" + findUser.getPassword(), null);
        }
        return new Result(false, "信息有误，请重新输入！", null);
    }
}
