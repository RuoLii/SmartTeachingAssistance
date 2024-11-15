package com.li.service;

import com.li.utils.Result;

public interface TagService {
    /**
     * 添加标签
     *
     * @param name 标签名
     * @return 处理结果
     */
    Result addTag(String name);

    /**
     * 查看所有标签
     *
     * @return 处理结果
     */
    Result showTagList();

    /**
     * 删除标签
     *
     * @param name 标签名
     * @return 处理结果
     */
    Result deleteTag(String name);
}
