package com.li.dao;

import com.li.dto.CourseDTO;
import com.li.po.Course;

import java.util.List;

public interface CourseDao {
    /**
     * 根据课程名查询对应的课程
     * @param name 课程名
     * @return 课程
     */
    Course selectCourseByName(String name);

    /**
     * 创建课程
     * @param course 课程
     * @return 是否创建成功
     */
    Boolean createCourse(Course course);

    /**
     * 查询当前用户创建的所有课程
     * @param userId 用户 id
     * @return 课程列表
     */
    List<Course> selectCourseByCreateUserId(Integer userId);
}
