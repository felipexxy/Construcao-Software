package com.applyandgrowth.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping(value="/recoverPass")
	public String recoverPassword() {
		return "recover_password";
	}

	@GetMapping("/settings")
	public String settings() {
		return "settings";
	}
}
