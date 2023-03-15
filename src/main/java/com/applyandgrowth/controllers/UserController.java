package com.applyandgrowth.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@RequestMapping(value="/recoverPass", method=RequestMethod.GET)
	public String recoverPassword() {
		return "recover_password";
	}

	@RequestMapping(value="/settings")
	public String settings() {
		return "settings";
	}
}
