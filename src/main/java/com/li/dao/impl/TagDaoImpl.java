package com.li.dao.impl;

import com.li.dao.TagDao;
import com.li.po.Tag;
import com.li.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return tag;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
