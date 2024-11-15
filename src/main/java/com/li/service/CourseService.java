package com.li.service;

import com.li.utils.Result;

public interface CourseService {
    /**
     * 创建课程
     *
     * @param courseName 课程名称
     * @param tagName    标签名称
     * @return 处理结果
     */
    Result createCourse(String courseName, String tagName, Integer userId);

    /**
     * 查询当前用户创建的所有课程
     *
     * @param userId 用户 id
     * @return 处理结果
     */
    Result selectCourseByCreateUserId(Integer userId);

    /**
     * 根据原课程名和新标签名修改课程信息
     *
     * @param courseName 原课程名
     * @param tagName    新标签名
     * @return 处理结果
     */
    Result updateCourseInfo(String courseName, String tagName);
}
