package com.li.dao;

import com.li.po.Clazz;
import com.li.po.Student;

import java.util.List;

public interface ClassDao {
    /**
     * 创建班级
     *
     * @param clazz 班级
     * @return 是否创建成功
     */
    Boolean createClass(Clazz clazz);

    /**
     * 获取未结课的和自己创建的班级的列表
     *
     * @param id 创建者id
     * @return 未结课的和创建者id创建的班级的列表
     */
    List<Clazz> getClassList(Integer id);

    /**
     * 添加学生
     *
     * @param student 学生
     * @return 是否添加成功
     */
    Boolean createStudent(Integer classId, Student student);

    /**
     * 查看学生列表
     *
     * @param classId 班级 id
     * @return 学生列表
     */
    List<Student> getStudentList(Integer classId);

    /**
     * 删除指定学生
     *
     * @param studentId 学生学号
     * @param name      学生姓名
     * @param classId   班级 id
     * @return 是否删除成功
     */
    Boolean deleteStudent(String studentId, String name, Integer classId);

    /**
     * 根据班级名结课
     * 0 -> 结课完成
     * 1 -> 找不到班级
     * 2 -> operationId != creatorId
     * 3 -> clazz.getIsClassOver == true
     * 4 -> 结课失败
     *
     * @param name        班级名
     * @param evaluate    评价内容
     * @param operationId 操作者 id
     * @return 0 1 2 3 4
     */
    Integer overClass(String name, String evaluate, Integer operationId);

    /**
     * 根据班级名修改班级信息
     *
     * @param clazz 修改后的班级
     * @return 是否修改成功
     */
    Boolean updateClass(Clazz clazz);
}
