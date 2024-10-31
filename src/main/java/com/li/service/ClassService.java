package com.li.service;

import com.li.po.Clazz;
import com.li.po.Student;
import com.li.utils.Result;

public interface ClassService {
    /**
     * 创建班级
     * @param c 班级
     * @return 处理结果
     */
    Result createClass(Clazz c);

    /**
     * 获取所有班级列表
     * @return 处理结果
     */
    Result getClassList(Integer id);

    Result createStudent(Integer classId, Student s);

    Result getStudentList(Integer id);

    Result deleteStudent(String studentId, String name, Integer classId);

    /**
     * 根据班级名结课
     * @param name 班级名称
     * @param evaluate 评价内容
     * @return 处理结果
     */
    Result overClass(String name, String evaluate, Integer operationId);

    /**
     * 根据班级名修改班级
     * @param c 修改后的班级
     * @return 处理结果
     */
    Result updateClass(Clazz c);
}
