package com.li.dao.impl;

import com.li.dao.CourseDao;
import com.li.dao.TagDao;
import com.li.po.Course;
import com.li.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    TagDao tagDao = new TagDaoImpl();

    @Override
    public Course selectCourseByName(String name) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        Course course = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from course where name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setTagId(resultSet.getInt("tag_id"));
                course.setStartTime(resultSet.getTimestamp("start_time"));
                course.setEndTime(resultSet.getTimestamp("end_time"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean createCourse(Course course) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into course (name, tag_id, start_time, end_time, create_user_id) values (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getTagId());
            preparedStatement.setDate(3, new java.sql.Date(course.getStartTime().getTime()));
            preparedStatement.setDate(4, null);
            preparedStatement.setInt(5, course.getCreateUserId());
            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> selectCourseByCreateUserId(Integer userId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Course> courseList = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from course where create_user_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setTagId(resultSet.getInt("tag_id"));
                course.setStartTime(resultSet.getTimestamp("start_time"));
                course.setEndTime(resultSet.getTimestamp("end_time"));
                courseList.add(course);
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;
    }

    @Override
    public Boolean updateCourse(Course course) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "update course set tag_id = ? where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, course.getTagId());
            preparedStatement.setInt(2, course.getId());

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
