package kr.donguk.covid;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.donguk.covid.service.COVID19Service;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private COVID19Service covid19Service;

	private Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		covid19Service.getData();
		return "home";
	}
	
}
