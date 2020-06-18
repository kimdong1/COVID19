package kr.donguk.covid.vo;

import lombok.Data;
/*
CREATE TABLE COVID19
(
    `idx`                  INT            NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `area`                 VARCHAR(50)    NULL        COMMENT '지역', 
    `Infected`             int(11)        NULL        COMMENT '감염자', 
    `Additional_infected`  int(11)        NULL        COMMENT '추가 감염자', 
    PRIMARY KEY (idx)
);
*/
@Data
public class COVID19 {
	private int idx;
	private String area;
	private String infected;
	private String additionalInfected;
}
