package com.li.module;

import com.li.dto.QuestionDTO;
import com.li.po.KnowledgePoint;
import com.li.po.Question;
import com.li.service.KnowledgePointService;
import com.li.service.QuestionService;
import com.li.service.impl.KnowledgePointServiceImpl;
import com.li.service.impl.QuestionServiceImpl;
import com.li.utils.Result;

import java.util.List;
import java.util.Scanner;

/**
 * 其他模块（其他+个人）
 */
public class OtherModule {
    private static final Scanner in = new Scanner(System.in);
    private static final KnowledgePointService knowledgePointService = new KnowledgePointServiceImpl();
    private static final QuestionService questionService = new QuestionServiceImpl();

    public static void start() {
        while (true) {
            System.out.println("*********  欢迎来到其他模块  *********");
            System.out.println("\t\t1. 知识点管理");
            System.out.println("\t\t2. 试题资源管理");
            System.out.println("*************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                knowledgePointManager();
            } else if (op == 2) {
                questionManager();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 知识点管理
     */
    private static void knowledgePointManager() {
        while (true) {
            System.out.println("*********  欢迎来到知识点管理  *********");
            System.out.println("\t\t1. 添加知识点");
            System.out.println("\t\t2. 知识点列表");
            System.out.println("\t\t3. 删除知识点");
            System.out.println("*************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                addKnowledgePoint();
            } else if (op == 2) {
                knowledgePointListShow();
            } else if (op == 3) {
                deleteKnowledgePoint();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 添加知识点
     */
    private static void addKnowledgePoint() {
        System.out.println("*********  欢迎来到创建知识点  *********");
        System.out.println("请输入知识点内容: ");
        String content = in.next();
        Result result = knowledgePointService.addKnowledgePoint(content, LoginModule.getLoginUser().getId());
        System.out.println(result.getMsg());
    }

    /**
     * 知识点列表
     */
    private static void knowledgePointListShow() {
        while (true) {
            System.out.println("*********  欢迎来到知识点列表展示  *********");
            List<KnowledgePoint> knowledgePointList = (List<KnowledgePoint>) knowledgePointService.showKnowledgePointList().getData();
            for (int i = 1; i <= knowledgePointList.size(); i++) {
                KnowledgePoint knowledgePoint = knowledgePointList.get(i - 1);
                System.out.println(i + "——>知识点名称: " + knowledgePoint.getContent() + "；创建时间: " + knowledgePoint.getCreateTime());
            }
            System.out.println("输出 -1 返回主菜单");
            int op = in.nextInt();
            if (op == -1) break;
        }
    }

    /**
     * 删除知识点
     */
    private static void deleteKnowledgePoint() {
        System.out.println("*********  欢迎来到删除知识点  *********");
        System.out.println("请输入知识点内容: ");
        String content = in.next();
        Result result = knowledgePointService.deleteKnowledgePoint(content, LoginModule.getLoginUser().getId());
        System.out.println(result.getMsg());
    }

    /**
     * 试题资源管理
     */
    private static void questionManager() {
        while (true) {
            System.out.println("*********  欢迎来到试题资源管理  *********");
            System.out.println("\t\t1. 添加试题");
            System.out.println("\t\t2. 试题资源列表");
            System.out.println("\t\t3. 删除试题");
            System.out.println("*************************************");
            System.out.print("请选择你要进入的模块: (输出 -1 返回主菜单)");
            int op = in.nextInt();
            if (op == 1) {
                addQuestion();
            } else if (op == 2) {
                questionListShow();
            } else if (op == 3) {
                deleteQuestion();
            } else if (op == -1) {
                break;
            }
        }
    }

    /**
     * 添加试题
     */
    private static void addQuestion() {
        System.out.println("*********  欢迎来到添加试题  *********");
        System.out.println("请输入题干: ");
        String title = in.next();
        System.out.println("请输入 A 选项内容: ");
        String optionA = in.next();
        System.out.println("请输入 B 选项内容: ");
        String optionB = in.next();
        System.out.println("请输入 C 选项内容: ");
        String optionC = in.next();
        System.out.println("请输入 D 选项内容: ");
        String optionD = in.next();
        System.out.println("请输入正确选项（A、B、C、D）: ");
        char correctOption = in.next().charAt(0);
        System.out.println("请输入绑定的知识点内容: ");
        String content = in.next();

        Question question = new Question();
        question.setTitle(title);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setCorrectOption(correctOption);
        question.setCreateUserId(LoginModule.getLoginUser().getId());

        Result result = questionService.addQuestion(question, content);
        System.out.println(result.getMsg());
    }

    /**
     * 试题资源列表
     */
    private static void questionListShow() {
        while (true) {
            System.out.println("*********  欢迎来到试题列表展示  *********");

            Result result = questionService.showQuestionListByCreateUserId(LoginModule.getLoginUser().getId());
            List<QuestionDTO> questionList = (List<QuestionDTO>) result.getData();

            for (QuestionDTO question : questionList) {
                System.out.println("题干: " + question.getTitle() + "；\t选项 A: " + question.getOptionA() + "；选项 B: " + question.getOptionB() + "；选项 C: " + question.getOptionC() + "；选项 D: " + question.getOptionD() + "；正确选项: " + question.getCorrectOption() + "；\t绑定知识点: " + question.getKnowledgePointContent() + "；创建时间: " + question.getCreateTime());
            }

            System.out.println("输出 -1 返回主菜单");
            int op = in.nextInt();
            if (op == -1) break;
        }
    }

    /**
     * 删除试题
     */
    private static void deleteQuestion() {
        System.out.println("*********  欢迎来到删除试题资源  *********");
        System.out.println("请输入知识点内容: ");
        String content = in.next();
        Result result = questionService.deleteQuestion(content, LoginModule.getLoginUser().getId());
        System.out.println(result.getMsg());
    }
}
