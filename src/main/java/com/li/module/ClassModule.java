package com.li.module;

import com.li.po.Clazz;
import com.li.po.Student;
import com.li.service.ClassService;
import com.li.service.impl.ClassServiceImpl;
import com.li.utils.Result;

import java.util.List;
import java.util.Scanner;

/**
 * 班级模块
 */
public class ClassModule {
    private static final Scanner in = new Scanner(System.in);
    private static final ClassService classService = new ClassServiceImpl();

    public static void start() {
        while (true) {
            System.out.println("*********  欢迎来到班级模块  *********");
            System.out.println("\t\t1. 创建班级");
            System.out.println("\t\t2. 班级列表展示");
            System.out.println("\t\t3. 结课");
            System.out.println("\t\t4. 修改班级信息");
            System.out.println("*************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                createClass();
            } else if (op == 2) {
                classListShow();
            } else if (op == 3) {
                overClass();
            } else if (op == 4) {
                updateClass();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 创建班级
     */
    public static void createClass() {
        System.out.println("*********  欢迎来到创建班级  *********");
        Clazz clazz = new Clazz();
        clazz.setCreatorId(LoginModule.getLoginUser().getId());
        System.out.println("请输入班级名称: ");
        clazz.setName(in.next());
        System.out.println("请输入班级学生人数: ");
        clazz.setStudentMaxCount(in.nextInt());
        System.out.println("请输入班级地址: ");
        clazz.setClassAddress(in.next());

        Result res = classService.createClass(clazz);
        System.out.println(res.getMsg());
    }

    /**
     * 班级列表展示
     */
    public static void classListShow() {
        while (true) {
            System.out.println("*********  欢迎来到班级列表展示  *********");
            List<Clazz> classList = (List<Clazz>) classService.getClassList(LoginModule.getLoginUser().getId()).getData();
            for (int i = 0; i < classList.size(); i++) {
                System.out.println((i + 1) + "——>" + "班级名称: " + classList.get(i).getName() + "；学生现有人数: " + classList.get(i).getStudentCount() + "；班级地址: " + classList.get(i).getClassAddress());
            }
            System.out.println("*************************************");
            System.out.print("请选择你要进入的班级: (输出 -1 返回班级模块)");
            int op = in.nextInt();
            if (op > 0 && op <= classList.size()) {
                //  进入班级
                enterClass(classList.get(op - 1));
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 结课
     */
    private static void overClass() {
        System.out.println("*********  欢迎来到结课  *********");
        System.out.println("请输入班级名称: ");
        String name = in.next();
        System.out.println("请输入评价内容: ");
        String evaluate = in.next();
        Result result = classService.overClass(name, evaluate, LoginModule.getLoginUser().getId());
        System.out.println(result.getMsg());
    }

    /**
     * 修改班级信息
     */
    private static void updateClass() {
        System.out.println("*********  欢迎来到修改班级信息  *********");
        Clazz clazz = new Clazz();
        System.out.println("请输入原班级名称: ");
        clazz.setName(in.next());
        System.out.println("请输入新的班级人数: ");
        clazz.setStudentMaxCount(in.nextInt());
        System.out.println("请输入新的班级地址: ");
        clazz.setClassAddress(in.next());
        Result res = classService.updateClass(clazz);
        System.out.println(res.getMsg());
    }

    /**
     * 进入具体班级
     * @param clazz 班级对象
     */
    private static void enterClass(Clazz clazz) {
        while (true) {
            System.out.println("*********  欢迎进入 " + clazz.getName() + "  *********");
            System.out.println("\t\t1. 添加学生");
            System.out.println("\t\t2. 查看学生列表");
            System.out.println("\t\t3. 删除指定学生");
            System.out.println("*************************************");
            System.out.print("请选择你要进行的操作: (输出 -1 返回班级列表)");
            int op = in.nextInt();
            if (op == 1) {
                createStudent(clazz);
            } else if (op == 2) {
                studentListShow(clazz);
            } else if (op == 3) {
                deleteStudent(clazz);
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 添加学生
     * @param clazz 班级
     */
    private static void createStudent(Clazz clazz) {
        System.out.println("*********  欢迎来到添加学生  *********");
        Student student = new Student();
        System.out.println("请输入学生学号: ");
        student.setStudentId(in.next());
        System.out.println("请输入学生姓名: ");
        student.setName(in.next());
        Result res = classService.createStudent(clazz.getId(), student);
        System.out.println(res.getMsg());
    }

    /**
     * 查看学生列表
     * @param clazz 班级
     */
    private static void studentListShow(Clazz clazz) {
        while (true) {
            System.out.println("*********  欢迎来到查看" + clazz.getName() + "下学生列表信息  *********");
            List<Student> studentList = (List<Student>) classService.getStudentList(clazz.getId()).getData();
            for (Student student : studentList) {
                System.out.println("学生学号: " + student.getStudentId() + "；学生姓名: " + student.getName());
            }
            System.out.println("输入 -1 返回");
            int op = in.nextInt();
            if (op == -1) {
                break;
            }
        }
    }

    /**
     * 删除学生
     * @param clazz 班级
     */
    private static void deleteStudent(Clazz clazz) {
        System.out.println("*********  欢迎来到删除" + clazz.getName() + "下指定学生  *********");
        System.out.println("请输入学生学号: ");
        String studentId = in.next();
        System.out.println("请输入学生姓名: ");
        String name = in.next();
        Result res = classService.deleteStudent(studentId, name, clazz.getId());
        System.out.println(res.getMsg());
    }
}
