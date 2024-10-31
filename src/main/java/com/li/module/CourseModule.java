package com.li.module;

import com.li.po.Clazz;
import com.li.po.Course;
import com.li.service.CourseService;
import com.li.service.impl.CourseServiceImpl;
import com.li.utils.Result;

import java.util.Scanner;

/**
 * 课程模块
 */
public class CourseModule {
    private static final Scanner in = new Scanner(System.in);
    private static final CourseService courseService = new CourseServiceImpl();

    public static void start() {
        while (true) {
            System.out.println("*********  欢迎来到课程模块  *********");
            System.out.println("\t\t1. 创建课程");
            System.out.println("\t\t2. 课程列表展示");
            System.out.println("\t\t3. 标签管理");
            System.out.println("\t\t4. 修改课程信息");
            System.out.println("*************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                createCourse();
            } else if (op == 2) {
                courseListShow();
            } else if (op == 3) {
                tagManager();
            } else if (op == 4) {
                updateCourse();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 创建课程
     */
    private static void createCourse() {
        System.out.println("*********  欢迎来到创建课程  *********");
        System.out.println("请输入课程名称: ");
        String courseName = in.next();
        System.out.println("请输入标签名称: ");
        String tagName = in.next();
        Result res = courseService.createCourse(courseName, tagName);
        System.out.println(res.getMsg());
    }

    /**
     * 课程列表展示
     */
    private static void courseListShow() {

    }

    /**
     * 标签管理
     */
    private static void tagManager() {

    }

    /**
     * 修改课程信息
     */
    private static void updateCourse() {

    }
}
