package com.li.dao.impl;

import com.li.dao.ClassDao;
import com.li.po.Clazz;
import com.li.po.Student;
import com.li.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassDaoImpl implements ClassDao {
    @Override
    public Boolean createClass(Clazz clazz) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into class (name, student_max_count, class_address, creator_id) values (?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, clazz.getName());
            preparedStatement.setInt(2, clazz.getStudentMaxCount());
            preparedStatement.setString(3, clazz.getClassAddress());
            preparedStatement.setInt(4, clazz.getCreatorId());
            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Clazz> getClassList(Integer id) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from class where creator_id = ? or is_class_over = false;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            List<Clazz> list = new ArrayList<>();

            while (resultSet.next()) {
                Clazz clazz = new Clazz();
                clazz.setId(resultSet.getInt("id"));
                clazz.setName(resultSet.getString("name"));
                clazz.setStudentCount(resultSet.getInt("student_count"));
                clazz.setStudentMaxCount(resultSet.getInt("student_max_count"));
                clazz.setClassAddress(resultSet.getString("class_address"));
                clazz.setCreatorId(resultSet.getInt("creator_id"));
                list.add(clazz);
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean createStudent(Integer classId, Student student) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from class where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();
            Clazz clazz = new Clazz();
            Integer maxCount = 0;
            Integer count = 0;
            while (resultSet.next()) {
                maxCount = resultSet.getInt("student_max_count");
                count = resultSet.getInt("student_count");
            }
            if (count >= maxCount) {
                return false;
            }

            String sql2 = "insert into student (student_id, name) values (?, ?);";
            preparedStatement = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());
            int i = preparedStatement.executeUpdate();

            if (i <= 0) {
                preparedStatement.close();
                JDBCUtil.release();
                return false;
            }
            // 获取学生表生成的 id
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int studentId = -1;
            if (generatedKeys.next()) {
                studentId = generatedKeys.getInt(1);
            }
            String sql3 = "insert into class_student (class_id, student_id) values (?, ?);";
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, studentId);
            int i2 = preparedStatement.executeUpdate();

            if (i2 <= 0) {
                preparedStatement.close();
                JDBCUtil.release();
                return false;
            }

            String sql4 = "update class set student_count = student_count + 1 where id = ?;";
            preparedStatement = connection.prepareStatement(sql4);
            preparedStatement.setInt(1, classId);
            int i3 = preparedStatement.executeUpdate();

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();
            return i3 > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudentList(Integer classId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from student where id = (select student_id from class_student where class_id = ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();

            List<Student> list = new ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setStudentId(resultSet.getString("student_id"));
                student.setName(resultSet.getString("name"));
                list.add(student);
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteStudent(String studentId, String name, Integer classId) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where student_id = ? and name = ? and id = (select student_id from class_student where class_id = ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, classId);
            int i = preparedStatement.executeUpdate();

            if (i <= 0) {
                preparedStatement.close();
                JDBCUtil.release();
                return false;
            }
            String sql2 = "update class set student_count = student_count - 1 where id = ?;";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, classId);
            int i2 = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();
            return i2 > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer overClass(String name, String evaluate, Integer operationId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from class where name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            Clazz clazz = null;
            while (resultSet.next()) {
                clazz = new Clazz();
                clazz.setId(resultSet.getInt("id"));
                clazz.setCreatorId(resultSet.getInt("creator_id"));
                clazz.setName(resultSet.getString("name"));
                clazz.setStudentCount(resultSet.getInt("student_count"));
                clazz.setStudentMaxCount(resultSet.getInt("student_max_count"));
                clazz.setClassAddress(resultSet.getString("class_address"));
                clazz.setIsClassOver(resultSet.getBoolean("is_class_over"));
                clazz.setEvaluate(resultSet.getString("evaluate"));
            }
            if (clazz == null) {
                //  找不到班级
                return 1;
            }
            if (!Objects.equals(clazz.getCreatorId(), operationId)) {
                //  不能结别人创建的班级
                return 2;
            }
            if (clazz.getIsClassOver()) {
                //  已经结课的班级不能再次结课
                return 3;
            }
            String sql2 = "update class set is_class_over = true, evaluate = ? where name = ?;";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, evaluate);
            preparedStatement.setString(2, name);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            JDBCUtil.release();
            if (i > 0) {
                //  结课完成
                return 0;
            }
            //  结课失败
            return 4;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean updateClass(Clazz clazz) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update class set student_max_count = ?, class_address = ? where name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, clazz.getStudentMaxCount());
            preparedStatement.setString(2, clazz.getClassAddress());
            preparedStatement.setString(3, clazz.getName());
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            JDBCUtil.release();
            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
