package si1lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginationCtrl {
	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	@RequestMapping("/home")
	public String goToHome(){
		return "home";
	}
	@RequestMapping("/contact")
	public String goToContact(){
		return "contact";
	}
	@RequestMapping("/createList")
	public String goToCreateList(){
		return "createList";
	}
}
