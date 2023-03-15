package com.applyandgrowth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";  // Fazer alguma lógica para redirecionar para o index
						 // de advertiser de acordo com a role?
	}
}
