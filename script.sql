-- 删除已存在的表
DROP TABLE IF EXISTS class_student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS user;

-- 创建用户表
CREATE TABLE user
(
    id       INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name     VARCHAR(50)                    NOT NULL,
    account  VARCHAR(50) UNIQUE             NOT NULL,
    password VARCHAR(255)                   NOT NULL,
    phone    VARCHAR(15),
    email    VARCHAR(100)
) COMMENT ='用户表';

-- 插入用户数据
INSERT INTO user (id, name, account, password, phone, email)
VALUES (1, 'ruoli', '10d1295970', '989760', '13777459989', '1069121098@qq.com');
INSERT INTO user (id, name, account, password, phone, email)
VALUES (2, 'admin', '1', '1', '13777459989', '1069121098@qq.com');

-- 创建学生表
CREATE TABLE student
(
    id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    studentId VARCHAR(50) UNIQUE             NOT NULL,
    name      VARCHAR(50)                    NOT NULL
) COMMENT = '学生表';

-- 创建班级表
CREATE TABLE class
(
    id              INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    creatorId       INT                            NOT NULL,
    name            VARCHAR(50) UNIQUE             NOT NULL,
    studentCount    INT     DEFAULT 0,
    studentMaxCount Int                            NOT NULL,
    classAddress    VARCHAR(255)                   NOT NULL,
    isClassOver     BOOLEAN DEFAULT FALSE,
    evaluate        VARCHAR(255),
    FOREIGN KEY (creatorId) REFERENCES user (id) ON DELETE CASCADE
) COMMENT = '班级表';

-- 插入班级数据
INSERT INTO class (id, creatorId, name, studentMaxCount, classAddress, isClassOver)
VALUES (1, 1, 'java1班', 50, 'A6E501', FALSE);
INSERT INTO class (creatorId, name, studentCount, studentMaxCount, classAddress, isClassOver)
VALUES (2, 'java2班', 0, 50, 'A6E502', FALSE);


-- 创建班级与学生关联表
CREATE TABLE class_student
(
    classId   INT NOT NULL,
    studentId INT NOT NULL,
    PRIMARY KEY (classId, studentId),
    FOREIGN KEY (classId) REFERENCES class (id) ON DELETE CASCADE,
    FOREIGN KEY (studentId) REFERENCES student (id) ON DELETE CASCADE
) COMMENT = '班级与学生关联表';


-- 创建标签表
CREATE TABLE tag
(
    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50)                    NOT NULL
) COMMENT = '标签表';

-- 创建课程表
CREATE TABLE course
(
    id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name      VARCHAR(50)                    NOT NULL,
    tagId     INT                            NOT NULL,
    startTime DATETIME                       NOT NULL,
    endTime   DATETIME,
    FOREIGN KEY (tagId) REFERENCES tag (id) ON DELETE CASCADE
) COMMENT = '课程表';
