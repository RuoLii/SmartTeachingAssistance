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
    student_id VARCHAR(50) UNIQUE             NOT NULL,
    name      VARCHAR(50)                    NOT NULL
) COMMENT = '学生表';

-- 创建班级表
CREATE TABLE class
(
    id              INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    creator_id       INT                            NOT NULL,
    name            VARCHAR(50) UNIQUE             NOT NULL,
    student_count    INT     DEFAULT 0,
    student_max_count Int                            NOT NULL,
    class_address    VARCHAR(255)                   NOT NULL,
    is_class_over     BOOLEAN DEFAULT FALSE,
    evaluate        VARCHAR(255),
    FOREIGN KEY (creator_id) REFERENCES user (id) ON DELETE CASCADE
) COMMENT = '班级表';

-- 插入班级数据
INSERT INTO class (id, creator_id, name, student_max_count, class_address, is_class_over)
VALUES (1, 1, 'java1班', 50, 'A6E501', FALSE);
INSERT INTO class (creator_id, name, student_count, student_max_count, class_address, is_class_over)
VALUES (2, 'java2班', 0, 50, 'A6E502', FALSE);


-- 创建班级与学生关联表
CREATE TABLE class_student
(
    class_id   INT NOT NULL,
    student_id INT NOT NULL,
    PRIMARY KEY (class_id, student_id),
    FOREIGN KEY (class_id) REFERENCES class (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE
) COMMENT = '班级与学生关联表';


-- 创建标签表
CREATE TABLE tag
(
    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50)                    NOT NULL
) COMMENT = '标签表';

INSERT INTO tag (id, name)
VALUES (1, 'Java');


-- 创建课程表
CREATE TABLE course
(
    id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name      VARCHAR(50)                    NOT NULL,
    tag_id     INT                            NOT NULL,
    start_time DATETIME                       NOT NULL,
    end_time   DATETIME,
    create_user_id INT NOT NULL ,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE,
    FOREIGN KEY (create_user_id) REFERENCES user (id) ON DELETE CASCADE
) COMMENT = '课程表';

INSERT INTO course (name, tag_id, start_time, end_time, create_user_id)
VALUES ('程序语言设计', 1, NOW(), null, 2),
       ('程序语言设计', 1, NOW(), NOW(), 2)