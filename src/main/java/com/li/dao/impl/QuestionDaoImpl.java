package com.li.dao.impl;

import com.li.dao.QuestionDao;
import com.li.po.Question;
import com.li.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @Override
    public Question selectQuestionByKnowledgePointId(Integer knowledgePointId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        Question question = null;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from question where knowledge_point_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, knowledgePointId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setKnowledgePointId(resultSet.getInt("knowledge_point_id"));
                question.setTitle(resultSet.getString("title"));
                question.setOptionA(resultSet.getString("option_a"));
                question.setOptionB(resultSet.getString("option_b"));
                question.setOptionC(resultSet.getString("option_c"));
                question.setOptionD(resultSet.getString("option_d"));
                question.setCorrectOption(resultSet.getString("correct_option").charAt(0));
                question.setCreateTime(resultSet.getTimestamp("create_time"));
                question.setCreateUserId(resultSet.getInt("create_user_id"));
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.release();

            return question;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean insertQuestion(Question question) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into question (knowledge_point_id, title, option_a, option_b, option_c, option_d, correct_option, create_time, create_user_id) values (?, ?, ?, ?, ?, ?, ?, now(), ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, question.getKnowledgePointId());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getOptionA());
            preparedStatement.setString(4, question.getOptionB());
            preparedStatement.setString(5, question.getOptionC());
            preparedStatement.setString(6, question.getOptionD());
            preparedStatement.setString(7, String.valueOf(question.getCorrectOption()));
            preparedStatement.setInt(8, question.getCreateUserId());

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> selectQuestionByCreateUserId(Integer createUserId) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Question> list = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from question where create_user_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, createUserId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setKnowledgePointId(resultSet.getInt("knowledge_point_id"));
                question.setTitle(resultSet.getString("title"));
                question.setOptionA(resultSet.getString("option_a"));
                question.setOptionB(resultSet.getString("option_b"));
                question.setOptionC(resultSet.getString("option_c"));
                question.setOptionD(resultSet.getString("option_d"));
                question.setCorrectOption(resultSet.getString("correct_option").charAt(0));
                question.setCreateTime(resultSet.getTimestamp("create_time"));
                question.setCreateUserId(resultSet.getInt("create_user_id"));
                list.add(question);
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
    public Boolean deleteQuestion(Integer questionId) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from question where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, questionId);

            int i = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.release();

            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
