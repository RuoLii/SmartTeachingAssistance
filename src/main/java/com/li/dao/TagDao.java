package com.li.dao;

import com.li.po.Tag;

import java.util.List;

public interface TagDao {
    /**
     * 根据标签 id 查询对应的标签对象
     *
     * @param id 标签 id
     * @return 标签对象
     */
    Tag selectTagById(Integer id);

    /**
     * 根据标签名查询对应的标签对象
     *
     * @param name 标签名
     * @return 标签对象
     */
    Tag selectTagByName(String name);

    /**
     * 根据标签名创建标签
     *
     * @param name 标签名
     * @return 是否创建成功
     */
    Boolean insertTagByName(String name);

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    List<Tag> selectAllTag();

    /**
     * 根据标签名删除标签
     *
     * @param name 标签名
     * @return 是否删除成功
     */
    Boolean deleteTagByName(String name);
}
