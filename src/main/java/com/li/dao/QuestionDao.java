package com.li.dao;

import com.li.po.Question;

import java.util.List;

public interface QuestionDao {

    /**
     * 根据知识点 id 查询试题
     * @param knowledgePointId  知识点 id
     * @return 试题实体类
     */
    Question selectQuestionByKnowledgePointId(Integer knowledgePointId);

    /**
     * 添加试题
     * @param question 试题对象
     * @return 是否添加成功
     */
    Boolean insertQuestion(Question question);

    /**
     * 根据创建者 id 查询试题
     * @param createUserId 创建者 id
     * @return 试题列表
     */
    List<Question> selectQuestionByCreateUserId(Integer createUserId);

    Boolean deleteQuestion(Integer questionId);
}
