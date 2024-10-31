package com.li.service.impl;

import com.li.dao.ClassDao;
import com.li.dao.impl.ClassDaoImpl;
import com.li.po.Clazz;
import com.li.po.Student;
import com.li.service.ClassService;
import com.li.utils.Result;

import java.util.List;


public class ClassServiceImpl implements ClassService {
    ClassDao classDao = new ClassDaoImpl();

    @Override
    public Result createClass(Clazz c) {
        Boolean flag = classDao.createClass(c);
        if (flag) {
            return new Result(true, "班级创建成功！", null);
        }
        return new Result(true, "输入信息有误，请重新输入！", null);
    }

    @Override
    public Result getClassList(Integer id) {
        return new Result(true, "", classDao.getClassList(id));
    }

    @Override
    public Result createStudent(Integer classId, Student s) {
        //  该班级的目前总人数不能大于创建班级时设置的总人数，若大于，则提示班级学生数已满，无法添加
        Boolean flag = classDao.createStudent(classId, s);
        if (flag) {
            return new Result(true, "添加成功！", null);
        }
        return new Result(false, "班级学生数已满！添加失败！", null);
    }

    @Override
    public Result getStudentList(Integer id) {
        return new Result(true, "", classDao.getStudentList(id));
    }

    @Override
    public Result deleteStudent(String studentId, String name, Integer classId) {
        Boolean flag = classDao.deleteStudent(studentId, name, classId);
        if (flag) {
            return new Result(true, "删除成功！", null);
        }
        return new Result(true, "该学生不存在，删除失败！", null);
    }

    @Override
    public Result overClass(String name, String evaluate, Integer operationId) {
        Integer i = classDao.overClass(name, evaluate, operationId);
        if (i == 0) {
            return new Result(true, "结课完成！", null);
        } else if (i == 1) {
            return new Result(false, "找不到该班级！", null);
        } else if (i == 2) {
            return new Result(false, "无法结别人创建的班级！", null);
        } else if (i == 3) {
            return new Result(false, "已经结课的班级无法再次结课！", null);
        }
        return new Result(false, "结课失败！", null);
    }

    @Override
    public Result updateClass(Clazz c) {
        if (classDao.updateClass(c)) {
            return new Result(true, "修改成功！", null);
        }
        return new Result(false, "修改失败，请检查班级名是否存在！", null);
    }
}
