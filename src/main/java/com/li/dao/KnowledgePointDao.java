package com.li.dao;

import com.li.po.KnowledgePoint;

import java.util.List;

public interface KnowledgePointDao {
    /**
     * 根据知识点内容查询对应的知识点
     *
     * @param content 知识点内容
     * @return 知识点对象
     */
    KnowledgePoint selectKnowledgePointByContent(String content);

    /**
     * 根据知识点内容创建知识点
     *
     * @param content 知识点内容
     * @param userId  创建者 id
     * @return 是否创建成功
     */
    Boolean insertKnowledgePoint(String content, Integer userId);

    /**
     * 查询所有知识点
     *
     * @return 知识点列表
     */
    List<KnowledgePoint> selectAllKnowledgePoint();

    /**
     * 根据知识点内容删除知识点
     *
     * @param content 知识点内容
     * @return 是否删除成功
     */
    Boolean deleteKnowledgePoint(String content);

    /**
     * 根据知识点 id 查询对应的知识点
     *
     * @param id 知识点 id
     * @return 知识点对象
     */
    KnowledgePoint selectKnowledgePointById(Integer id);
}
