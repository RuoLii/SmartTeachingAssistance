package com.li.dao;

import com.li.po.Tag;

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
}
