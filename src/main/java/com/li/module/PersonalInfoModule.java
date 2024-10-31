package com.li.module;


import com.li.po.User;

import java.util.Scanner;

public class PersonalInfoModule {
    private static final Scanner in = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("*********  欢迎来到个人信息  *********");
            User user = LoginModule.getLoginUser();
            System.out.println("昵称: " + user.getName());
            System.out.println("账号: " + user.getAccount());
            System.out.println("手机号: " + user.getPhone());
            System.out.println("邮箱: " + user.getEmail());
            System.out.println("*************************************");
            System.out.println("输入 -1 返回");
            int op = in.nextInt();
            if (op == -1) {
                break;
            }
        }
    }
}
