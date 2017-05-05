package com.bookstore.controller;

import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController combines @Controller and @ResponseBody => web requests returning data rather than a view.
public class AppController {

	@RequestMapping("/")
	String home(ModelMap model) {
		model.addAttribute("title","BookStore");
		return "index";
	}
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;	
	}
}