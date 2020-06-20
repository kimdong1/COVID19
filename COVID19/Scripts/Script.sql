CREATE TABLE COVID19
(
    `idx`                  INT            NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `area`                 VARCHAR(50)    NULL        COMMENT '지역', 
    `Infected`             VARCHAR(50)    NULL        COMMENT '감염자', 
    `AdditionalInfected`  VARCHAR(50)    NULL        COMMENT '추가 감염자', 
    `regDate`             timestamp      NULL        COMMENT '등록일', 
    PRIMARY KEY (idx)
);
drop table COVID19;

select * from covid19;