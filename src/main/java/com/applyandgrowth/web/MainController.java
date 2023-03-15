package com.applyandgrowth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index_client")
	public String indexClient() {
		return "index";
	}

	@GetMapping("/index_advertiser")
	public String home() {
		return "index_advertiser";
	}
}
