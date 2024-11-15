package com.li.service.impl;

import com.li.dao.KnowledgePointDao;
import com.li.dao.QuestionDao;
import com.li.dao.impl.KnowledgePointDaoImpl;
import com.li.dao.impl.QuestionDaoImpl;
import com.li.po.KnowledgePoint;
import com.li.service.KnowledgePointService;
import com.li.utils.Result;

import java.util.Objects;

public class KnowledgePointServiceImpl implements KnowledgePointService {
    private final KnowledgePointDao knowledgePointDao = new KnowledgePointDaoImpl();
    private final QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public Result addKnowledgePoint(String content, Integer userId) {
        KnowledgePoint knowledgePoint = knowledgePointDao.selectKnowledgePointByContent(content);
        if (knowledgePoint != null) {
            return new Result(false, "该知识点内容已存在！", null);
        }
        if (knowledgePointDao.insertKnowledgePoint(content, userId)) {
            return new Result(true, "创建成功！", null);
        }
        return new Result(false, "创建失败！", null);
    }

    @Override
    public Result showKnowledgePointList() {
        return new Result(true, "", knowledgePointDao.selectAllKnowledgePoint());
    }

    @Override
    public Result deleteKnowledgePoint(String content, Integer userId) {
        KnowledgePoint knowledgePoint = knowledgePointDao.selectKnowledgePointByContent(content);
        if (knowledgePoint == null) {
            return new Result(false, "知识点不存在！", null);
        }
        if (!Objects.equals(knowledgePoint.getCreateUserId(), userId)) {
            return new Result(false, "对不起，您不能删除别人创建的知识点！", null);
        }
        if (questionDao.selectQuestionByKnowledgePointId(knowledgePoint.getId()) != null) {
            return new Result(false, "对不起，该知识点已绑定题目，无法删除", null);
        }
        if (knowledgePointDao.deleteKnowledgePoint(content)) {
            return new Result(true, "删除成功！", null);
        }
        return new Result(false, "删除失败！", null);
    }
}
