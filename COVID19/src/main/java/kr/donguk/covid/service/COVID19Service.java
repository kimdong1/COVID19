package kr.donguk.covid.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.donguk.covid.mapper.COVID19Mapper;
import kr.donguk.covid.vo.COVID19;

@Service
public class COVID19Service {

	private Logger logger = LoggerFactory.getLogger(COVID19Service.class);
	
	@Autowired
	private COVID19Mapper covid19Mapper;
	
	public void getData(){
		String name = "";
		String num = "";
		String before = "";
		
		try {
			String url = ("http://ncov.mohw.go.kr/");
			
			Document doc = Jsoup.connect(url).get(); 
			Elements el = doc.select("div.rpsam_graph");
			
			name = getElement(el.select("span.name"));
			num = getElement(el.select("span.num"));
			before = getElement(el.select("span.before"));
			
			String[] nameArr = new String[el.select("span.name").size()]; 
			for(int i = 0; i < nameArr.length; i++) {
				nameArr[i] = name.split(",")[i];
			}
			String[] numArr = new String[el.select("span.num").size()]; 
			for(int i = 0; i < numArr.length; i++) {
				numArr[i] = num.split(",")[i];
			}
			String[] beforeArr = new String[el.select("span.before").size()]; 
			for(int i = 0; i < beforeArr.length; i++) {
				beforeArr[i] = before.split(",")[i];
			}
			
			COVID19 covid19 = new COVID19();
			for(int i = 0; i < 18; i++) {
				covid19.setArea(nameArr[i]);
				covid19.setInfected(numArr[i]);
				covid19.setAdditionalInfected(beforeArr[i]);
				
				covid19Mapper.covid19Insert(covid19);
			}
			logger.info("등록 완료");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	private String getElement(Elements el) {
		String select = "";
		int index = 0;
		for(Element e : el) {
			if(index<el.size()-1) {
				select += e.text().replaceAll(",", "") + ",";
			}else {
				select += e.text();
			}
			index++;
		}
		return select;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
