package com.li.service;

import com.li.po.Course;
import com.li.utils.Result;

public interface CourseService {
    /**
     * 创建课程
     * @param courseName 课程名称
     * @param tagName 标签名称
     * @return 处理结果
     */
    Result createCourse(String courseName, String tagName);
}
