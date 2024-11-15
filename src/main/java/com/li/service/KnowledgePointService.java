package com.li.service;

import com.li.utils.Result;

public interface KnowledgePointService {
    /**
     * 根据知识点内容查询对应的知识点
     *
     * @param content 知识点内容
     * @param userId  创建者 id
     * @return 处理结果
     */
    Result addKnowledgePoint(String content, Integer userId);

    /**
     * 查询所有知识点
     *
     * @return 处理结果
     */
    Result showKnowledgePointList();

    /**
     * 根据知识点内容删除知识点
     *
     * @param content 知识点内容
     * @return 处理结果
     */
    Result deleteKnowledgePoint(String content, Integer userId);
}
