package com.li.module;

import com.li.po.User;
import com.li.service.UserService;
import com.li.service.impl.UserServiceImpl;
import com.li.utils.Result;
import lombok.Getter;

import java.util.Scanner;

public class LoginModule {
    @Getter
    private static User loginUser;

    private static final Scanner in = new Scanner(System.in);

    private static UserService userService = new UserServiceImpl();

    public static void start() {
        while (true) {
            System.out.println("*********  欢迎使用 iwei 智慧教辅系统  *********");
            System.out.println("\t\t1. 登录");
            System.out.println("\t\t2. 注册");
            System.out.println("\t\t3. 忘记密码");
            System.out.println("\t\t4. 退出系统");
            System.out.println("***********************************************");
            System.out.print("请选择你要进入的模块: ");
            int op = in.nextInt();
            if (op == 1) {
                login();
            } else if (op == 2) {
                register();
            } else if (op == 3) {
                forget();
            } else if (op == 4) {
                break;
            }
        }
        System.out.println("已退出！");
    }

    /**
     * 登录
     */
    private static void login() {
        System.out.println("*********  欢迎登录  *********");
        System.out.println("请输入账号: ");
        String account = in.next();
        System.out.println("请输入密码: ");
        String password = in.next();
        Result res = userService.login(account, password);
        if (res.getState()) {
            System.out.println("登录成功！");
            //  进入主菜单栏
            loginUser = (User) res.getData();
            MainMenuModule.start();
        } else {
            System.out.println(res.getMsg());
        }
    }

    /**
     * 注册
     */
    private static void register() {
        System.out.println("*********  欢迎注册  *********");
        User user = new User();
        System.out.println("请输入昵称: ");
        user.setName(in.next());
        System.out.println("请输入密码: ");
        user.setPassword(in.next());
        System.out.println("请输入确认密码: ");
        user.setRePassword(in.next());
        System.out.println("请输入手机号: ");
        user.setPhone(in.next());
        System.out.println("请输入邮箱: ");
        user.setEmail(in.next());

        Result res = userService.register(user);
        System.out.println(res.getMsg());
    }

    /**
     * 忘记密码
     */
    private static void forget() {
        System.out.println("*********  欢迎来到忘记密码  *********");
        User user = new User();
        System.out.println("请输入账号: ");
        user.setAccount(in.next());
        System.out.println("请输入手机号: ");
        user.setPhone(in.next());
        System.out.println("请输入邮箱: ");
        user.setEmail(in.next());

        Result res = userService.forgetPassword(user);
        System.out.println(res.getMsg());
    }
}
