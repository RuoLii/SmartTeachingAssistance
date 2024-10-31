package com.li.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id;
    private Integer creatorId;
    private String name;
    private Integer studentCount;
    private Integer studentMaxCount;
    private String classAddress;
    private Boolean isClassOver;
    private String evaluate;
}
