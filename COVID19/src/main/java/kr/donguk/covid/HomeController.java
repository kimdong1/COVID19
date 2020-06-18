package kr.donguk.covid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		StringBuffer sb = new StringBuffer();
		try {
			String url = ("http://ncov.mohw.go.kr/");
			
			Document doc = Jsoup.connect(url).get(); 
			Elements el = doc.select("div.rpsam_graph");
			
			
			for(Element e : el.select("button")) {
				System.out.println(e.html());
				sb.append(e.text()+"<br />");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("text",sb.toString());
		return "home";
	}
	
}
