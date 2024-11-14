package com.li.service.impl;

import com.li.dao.CourseDao;
import com.li.dao.TagDao;
import com.li.dao.impl.CourseDaoImpl;
import com.li.dao.impl.TagDaoImpl;
import com.li.dto.CourseDTO;
import com.li.po.Course;
import com.li.po.Tag;
import com.li.service.CourseService;
import com.li.utils.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    TagDao tagDao = new TagDaoImpl();
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public Result createCourse(String courseName, String tagName, Integer userId) {
        Tag tag = tagDao.selectTagByName(tagName);
        if (tag == null) {
            return new Result(false, "标签名不存在！", null);
        }
        Course course = courseDao.selectCourseByName(courseName);
        if (course != null) {
            return new Result(false, "课程名称不能重复！", null);
        }

        Course newCourse = new Course();
        newCourse.setName(courseName);
        newCourse.setTagId(tag.getId());
        newCourse.setStartTime(new Date());
        newCourse.setCreateUserId(userId);

        if (courseDao.createCourse(newCourse)) {
            return new Result(true, "创建成功！", null);
        }
        return new Result(false, "创建失败，请稍后重试！", null);
    }

    @Override
    public Result selectCourseByCreateUserId(Integer userId) {
        List<Course> courseList = courseDao.selectCourseByCreateUserId(userId);
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseList.forEach(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            courseDTO.setTagName(tagDao.selectTagById(course.getTagId()).getName());
            courseDTO.setStartTime(course.getStartTime());
            courseDTO.setEndTime(course.getEndTime());
            courseDTOList.add(courseDTO);
        });
        return new Result(true, "", courseDTOList);
    }
}
