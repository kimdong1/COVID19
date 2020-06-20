package kr.donguk.covid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.donguk.covid.service.COVID19Service;
import kr.donguk.covid.vo.COVID19;

@Controller
public class HomeController {
	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private COVID19Service covid19Service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<COVID19> list = covid19Service.covid19SelectList();
		String total = covid19Service.getNum(0);
		String dead = covid19Service.getNum(3);
		String subNum = covid19Service.getSubNum();

		model.addAttribute("list",list);
		model.addAttribute("total",total);
		model.addAttribute("dead",dead);
		model.addAttribute("subNum",subNum);
		return "home";
	}

	@RequestMapping(value = "/firstData", method = RequestMethod.POST)
	public String firstData() {
		covid19Service.firstData();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/dataUpdate", method = RequestMethod.POST)
	public String dataUpdate() {
		covid19Service.dataUpdate();
		return "redirect:/";
	}
	
	
	
}
