package com.brummer.poc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String index(Model model) {
		logger.info("inside of / method");
		return "index";
	}
	
	@RequestMapping("/index")
	public String index2(Model model) {
		logger.info("inside of /index method");
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		logger.info("inside of /home method");
		return "home";
	}
	
}