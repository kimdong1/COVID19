package kr.donguk.covid.vo;

import java.util.Date;

import lombok.Data;
@Data
public class COVID19 {
	private int idx;
	private String area;
	private String infected;
	private String additionalInfected;
	private Date regDate;
	private String total;
	private String dead;
	
}
