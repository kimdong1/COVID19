package kr.donguk.covid.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	public void firstData(){
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
			
			String[] nameArr = stringToArray(name, el, "span.name"); 
			String[] numArr = stringToArray(num, el, "span.num"); 
			String[] beforeArr = stringToArray(before, el, "span.before");
			
			COVID19 covid19 = new COVID19();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm");
			Date from = new Date();
			Date to = new Date();
			try {
				to = sdf.parse(sdf.format(from));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < 18; i++) {
				covid19.setArea(nameArr[i]);
				covid19.setInfected(numArr[i]);
				covid19.setAdditionalInfected(beforeArr[i]);
				covid19.setRegDate(to);
				
				covid19Mapper.covid19Insert(covid19);
				logger.info("list : {}",covid19);
			}
			logger.info("등록 완료!!!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String[] stringToArray(String str, Elements el, String tag) {
		String[] arr = new String[el.select(tag).size()]; 
		for(int i = 0; i < arr.length; i++) {
			arr[i] = str.split(",")[i];
		}
		return arr;
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

	
	public void dataUpdate() {
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
			
			String[] nameArr = stringToArray(name, el, "span.name"); 
			String[] numArr = stringToArray(num, el, "span.num"); 
			String[] beforeArr = stringToArray(before, el, "span.before");
			
			COVID19 covid19 = new COVID19();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm");
			Date from = new Date();
			Date to = new Date();
			try {
				to = sdf.parse(sdf.format(from));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < 18; i++) {
				covid19.setArea(nameArr[i]);
				covid19.setInfected(numArr[i]);
				covid19.setAdditionalInfected(beforeArr[i]);
				covid19.setRegDate(to);
				
				covid19Mapper.covid19Update(covid19);
				logger.info("{}",covid19);
			}
			logger.info("업데이트 완료!!!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<COVID19> covid19SelectList(){
		List<COVID19> list = covid19Mapper.covid19SelectList();
		logger.info("뿌려주는 쿼리 : {}",list);
		return list;
	}
	
	public String getNum(int index) {
		String url = ("http://ncov.mohw.go.kr/");
		String str = "";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements el = doc.select("ul.cityinfo");
			String[] strArr = getElement(el.select("span.num")).split(",");
			logger.info("\n===================\n{}\n====================",getElement(el.select("span.num")));
			str= strArr[index]; // 0 & 3 0 = total, 3 = dead
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return str;
	}
	public String getSubNum() {
		String url = ("http://ncov.mohw.go.kr/");
		String str = "";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements el = doc.select("span.sub_num");
			String[] strArr =  getElement(el.select("span.sub_num")).split(",");
			str = strArr[0];
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
