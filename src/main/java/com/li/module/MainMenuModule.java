package com.li.module;

import java.util.Scanner;

/**
 * 主菜单
 */
public class MainMenuModule {
    public static void start() {
        while (true) {
            System.out.println("*********  欢迎进入智慧教辅系统  *********");
            System.out.println("\t\t1. 班级模块");
            System.out.println("\t\t2. 课程模块");
            System.out.println("\t\t3. 其他模块");
            System.out.println("\t\t4. 个人信息");
            System.out.println("****************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 退出登录)");
            Scanner in = new Scanner(System.in);
            int op = in.nextInt();
            if (op == 1) {
                ClassModule.start();
            } else if (op == 2) {
                CourseModule.start();
            } else if (op == 3) {
                OtherModule.start();
            } else if (op == 4) {
                PersonalInfoModule.start();
            } else if (op == -1) {
                break;
            }
        }
    }
}
