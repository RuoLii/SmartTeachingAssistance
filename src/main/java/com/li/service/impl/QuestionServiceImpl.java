package com.li.service.impl;

import com.li.dao.KnowledgePointDao;
import com.li.dao.QuestionDao;
import com.li.dao.impl.KnowledgePointDaoImpl;
import com.li.dao.impl.QuestionDaoImpl;
import com.li.dto.QuestionDTO;
import com.li.po.KnowledgePoint;
import com.li.po.Question;
import com.li.service.QuestionService;
import com.li.utils.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionServiceImpl implements QuestionService {
    private static final QuestionDao questionDao = new QuestionDaoImpl();
    private static final KnowledgePointDao knowledgePointDao = new KnowledgePointDaoImpl();

    @Override
    public Result addQuestion(Question question, String content) {
        char correctOption = question.getCorrectOption();
        if (Character.toUpperCase(correctOption) != 'A' &&
            Character.toUpperCase(correctOption) != 'B' &&
            Character.toUpperCase(correctOption) != 'C' &&
            Character.toUpperCase(correctOption) != 'D') {
            return new Result(false, "正确选项的输入只能是 A, B, C, D 其中一种！", null);
        }

        KnowledgePoint knowledgePoint = knowledgePointDao.selectKnowledgePointByContent(content);
        if (knowledgePoint == null) {
            return new Result(false, "该知识点不存在！", null);
        }
        if (questionDao.selectQuestionByKnowledgePointId(knowledgePoint.getId()) != null) {
            return new Result(false, "该知识点已被其他试题绑定，无法再进行绑定！", null);
        }
        question.setKnowledgePointId(knowledgePoint.getId());
        if (questionDao.insertQuestion(question)) {
            return new Result(true, "添加试题成功！", null);
        }
        return new Result(false, "添加试题失败！", null);
    }

    @Override
    public Result showQuestionListByCreateUserId(Integer userId) {
        List<Question> questionList = questionDao.selectQuestionByCreateUserId(userId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(question.getId());
            questionDTO.setKnowledgePointContent(knowledgePointDao.selectKnowledgePointById(question.getKnowledgePointId()).getContent());
            questionDTO.setTitle(question.getTitle());
            questionDTO.setOptionA(question.getOptionA());
            questionDTO.setOptionB(question.getOptionB());
            questionDTO.setOptionC(question.getOptionC());
            questionDTO.setOptionD(question.getOptionD());
            questionDTO.setCorrectOption(question.getCorrectOption());
            questionDTO.setCreateTime(question.getCreateTime());
            questionDTOList.add(questionDTO);
        }
        return new Result(true, "", questionDTOList);
    }

    @Override
    public Result deleteQuestion(String content, Integer userId) {
        KnowledgePoint knowledgePoint = knowledgePointDao.selectKnowledgePointByContent(content);
        if (knowledgePoint == null) {
            return new Result(false, "不存在该知识点！", null);
        }
        Question question = questionDao.selectQuestionByKnowledgePointId(knowledgePoint.getId());
        if (question == null) {
            return new Result(false, "该知识点未绑定试题！", null);
        }
        if (!Objects.equals(question.getCreateUserId(), userId)) {
            return new Result(false, "您不能删除别人创建的试题资源！", null);
        }
        if (questionDao.deleteQuestion(question.getId())) {
            return new Result(true, "删除成功！", null);
        }
        return new Result(false, "删除失败！", null);
    }
}
