package com.li.service;

import com.li.po.Question;
import com.li.utils.Result;

public interface QuestionService {
    /**
     * 添加试题
     * @param question 试题实体类
     * @param content 知识点内容
     * @return 处理结果
     */
    Result addQuestion(Question question, String content);

    /**
     * 根据创建者 id 查询试题资源
     * @param userId 创建者 id
     * @return 处理结果
     */
    Result showQuestionListByCreateUserId(Integer userId);

    /**
     * 根据知识点内容删除试题
     * @param content 知识点内容
     * @return 处理结果
     */
    Result deleteQuestion(String content, Integer userId);
}
