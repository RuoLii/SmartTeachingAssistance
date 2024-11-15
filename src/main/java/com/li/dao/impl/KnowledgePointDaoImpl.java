package com.li.dao.impl;

import com.li.dao.KnowledgePointDao;
import com.li.po.KnowledgePoint;
import com.li.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KnowledgePointDaoImpl implements KnowledgePointDao {

    @Override
    public KnowledgePoint selectKnowledgePointByContent(String content) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        KnowledgePoint knowledgePoint = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from knowledge_point where content=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, content);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                knowledgePoint = new KnowledgePoint();
                knowledgePoint.setId(resultSet.getInt("id"));
                knowledgePoint.setContent(resultSet.getString("content"));
                knowledgePoint.setCreateTime(resultSet.getTimestamp("create_time"));
                knowledgePoint.setCreateUserId(resultSet.getInt("create_user_id"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return knowledgePoint;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean insertKnowledgePoint(String content, Integer userId) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into knowledge_point (content, create_time, create_user_id) values (?, now(), ?);";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, userId);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<KnowledgePoint> selectAllKnowledgePoint() {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<KnowledgePoint> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from knowledge_point;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                KnowledgePoint knowledgePoint = new KnowledgePoint();
                knowledgePoint.setId(resultSet.getInt("id"));
                knowledgePoint.setContent(resultSet.getString("content"));
                knowledgePoint.setCreateTime(resultSet.getTimestamp("create_time"));
                knowledgePoint.setCreateUserId(resultSet.getInt("create_user_id"));
                list.add(knowledgePoint);
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
    public Boolean deleteKnowledgePoint(String content) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from knowledge_point where content = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, content);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KnowledgePoint selectKnowledgePointById(Integer id) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        KnowledgePoint knowledgePoint = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from knowledge_point where id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                knowledgePoint = new KnowledgePoint();
                knowledgePoint.setId(resultSet.getInt("id"));
                knowledgePoint.setContent(resultSet.getString("content"));
                knowledgePoint.setCreateTime(resultSet.getTimestamp("create_time"));
                knowledgePoint.setCreateUserId(resultSet.getInt("create_user_id"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return knowledgePoint;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
