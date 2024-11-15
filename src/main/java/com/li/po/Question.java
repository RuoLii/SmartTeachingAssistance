package com.li.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private Integer knowledgePointId;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char correctOption;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/shanghai")
    private Date createTime;
    private Integer createUserId;
}
