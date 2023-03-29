package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@Autowired

	@GetMapping("/settings")
	public String settings() {
		return "settings";
	}
	
	@GetMapping("/status")
	public String status() {
		return "status-client";
	}
	
}
