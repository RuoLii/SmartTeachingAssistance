package com.li.service.impl;

import com.li.dao.TagDao;
import com.li.dao.impl.TagDaoImpl;
import com.li.po.Tag;
import com.li.service.TagService;
import com.li.utils.Result;


public class TagServiceImpl implements TagService {
    TagDao tagDao = new TagDaoImpl();

    @Override
    public Result addTag(String name) {
        Tag selectTag = tagDao.selectTagByName(name);
        if (selectTag != null) {
            return new Result(false, "该标签已存在！", null);
        }
        if (tagDao.insertTagByName(name)) {
            return new Result(true, "添加成功！", null);
        }
        return new Result(false, "添加失败！", null);
    }

    @Override
    public Result showTagList() {
        return new Result(true, "", tagDao.selectAllTag());
    }

    @Override
    public Result deleteTag(String name) {
        Tag selectTag = tagDao.selectTagByName(name);
        if (selectTag == null) {
            return new Result(false, "标签名不存在！", null);
        }
        Boolean res = tagDao.deleteTagByName(name);
        if (res) {
            return new Result(true, "删除成功！", null);
        }
        return new Result(false, "删除失败！", null);
    }
}
