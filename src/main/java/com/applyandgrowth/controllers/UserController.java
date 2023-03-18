package com.applyandgrowth.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping(value="/recover/enterEmail")
	public String recoverPassword1() {
		return "recover_password";
	}

	@GetMapping(value="/recover/verifyEmail")
	public String recoverPassword2() {
		return "recover_password_2";
	}

	@GetMapping(value="/recover/changePassword")
	public String recoverPassword3() {
		return "recover_password_3";
	}

	@GetMapping("/settings")
	public String settings() {
		return "settings";
	}
}
