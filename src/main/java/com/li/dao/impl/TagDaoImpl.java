package com.li.dao.impl;

import com.li.dao.TagDao;
import com.li.po.Tag;
import com.li.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {
    @Override
    public Tag selectTagById(Integer id) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        Tag tag = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from tag where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tag = new Tag();
                tag.setId(resultSet.getInt("id"));
                tag.setName(resultSet.getString("name"));
                tag.setCreateTime(resultSet.getTimestamp("create_time"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return tag;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tag selectTagByName(String name) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        Tag tag = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from tag where name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tag = new Tag();
                tag.setId(resultSet.getInt("id"));
                tag.setName(resultSet.getString("name"));
                tag.setCreateTime(resultSet.getTimestamp("create_time"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return tag;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean insertTagByName(String name) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into tag (name, create_time) values (?, now());";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tag> selectAllTag() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Tag> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from tag;;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt("id"));
                tag.setName(resultSet.getString("name"));
                tag.setCreateTime(resultSet.getTimestamp("create_time"));
                list.add(tag);
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
    public Boolean deleteTagByName(String name) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from tag where name = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
