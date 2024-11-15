package com.li.module;

import com.li.dto.CourseDTO;
import com.li.po.Clazz;
import com.li.po.Course;
import com.li.po.Tag;
import com.li.service.CourseService;
import com.li.service.TagService;
import com.li.service.impl.CourseServiceImpl;
import com.li.service.impl.TagServiceImpl;
import com.li.utils.Result;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 课程模块
 */
public class CourseModule {
    private static final Scanner in = new Scanner(System.in);
    private static final CourseService courseService = new CourseServiceImpl();
    private static final TagService tagService = new TagServiceImpl();

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
        Result res = courseService.createCourse(courseName, tagName, LoginModule.getLoginUser().getId());
        System.out.println(res.getMsg());
    }

    /**
     * 课程列表展示
     */
    private static void courseListShow() {
        while (true) {
            System.out.println("*********  欢迎来到课程列表展示  *********");
            List<CourseDTO> courseList = (List<CourseDTO>) courseService.selectCourseByCreateUserId(LoginModule.getLoginUser().getId()).getData();
            for (int i = 1; i <= courseList.size(); i++) {
                CourseDTO course = courseList.get(i - 1);
                System.out.print(i + "——>课程名称: " + course.getName() + "；标签名称: " + course.getTagName() + "；创建时间: " + course.getStartTime());
                if (course.getEndTime() != null) System.out.print("；修改时间: " + course.getEndTime());
                System.out.println();
            }
            System.out.println("输出 -1 返回主菜单");
            int op = in.nextInt();
            if (op == -1) break;
        }
    }

    /**
     * 标签管理
     */
    private static void tagManager() {
        while (true) {
            System.out.println("*********  欢迎来到标签管理  *********");
            System.out.println("\t\t1. 添加标签");
            System.out.println("\t\t2. 查看标签列表");
            System.out.println("\t\t3. 删除指定标签");
            System.out.println("*************************************");
            System.out.print("请选择你的操作: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                addTag();
            } else if (op == 2) {
                tagListShow();
            } else if (op == 3) {
                deleteTag();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 修改课程信息
     */
    private static void updateCourse() {
        System.out.println("*********  欢迎来到修改课程信息  *********");
        System.out.println("请输入原课程名称: ");
        String courseName = in.next();
        System.out.println("请输入新的标签名称: ");
        String tagName = in.next();
        Result result = courseService.updateCourseInfo(courseName, tagName);
        System.out.println(result.getMsg());
    }

    /**
     * 添加标签
     */
    private static void addTag() {
        System.out.println("*********  欢迎来到添加标签  *********");
        System.out.println("请输标签名称: ");
        String name = in.next();
        Result result = tagService.addTag(name);
        System.out.println(result.getMsg());
    }


    /**
     * 查看标签列表
     */
    private static void tagListShow() {
        while (true) {
            System.out.println("*********  欢迎来到查看标签列表  *********");
            Result result = tagService.showTagList();
            for (Tag tag : (List<Tag>) result.getData()) {
                System.out.println("标签名称: " + tag.getName() + "；创建时间: " + tag.getCreateTime());
            }
            System.out.println("输出 -1 返回主菜单");
            int op = in.nextInt();
            if (op == -1) break;
        }
    }

    /**
     * 删除指定标签
     */
    private static void deleteTag() {
        System.out.println("*********  欢迎来到删除标签  *********");
        System.out.println("请输标签名称: ");
        String name = in.next();
        Result result = tagService.deleteTag(name);
        System.out.println(result.getMsg());
    }
}
