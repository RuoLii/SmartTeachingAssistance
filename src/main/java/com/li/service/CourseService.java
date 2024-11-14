package com.li.service;

import com.li.utils.Result;

public interface CourseService {
    /**
     * 创建课程
     * @param courseName 课程名称
     * @param tagName 标签名称
     * @return 处理结果
     */
    Result createCourse(String courseName, String tagName, Integer userId);

    /**
     * 查询当前用户创建的所有课程
     * @param userId 用户 id
     * @return 课程列表
     */
    Result selectCourseByCreateUserId(Integer userId);
}
