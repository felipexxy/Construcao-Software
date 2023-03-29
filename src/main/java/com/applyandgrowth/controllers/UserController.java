package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.applyandgrowth.repository.UserRepository;
import com.applyandgrowth.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService er;

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
	
	@GetMapping("/status")
	public String status() {
		return "status-client";
	}

	@PostMapping("/recover/enterEmail")
	public ResponseEntity<String> sendPasswordResetLink(@RequestParam("email") String email) {
    // lógica para enviar o e-mail
    return ResponseEntity.ok().build();
}

	@RequestMapping(value = "/recover/changePassword", method=RequestMethod.POST)
	public String changepassword(String email, String password, String confirmPassword){
		if (!confirmPassword.equals(password)) {
			// Retorna a página do formulário com uma mensagem de erro
			return "recover_password_form";
		}
		er.setPassword(email, password);

		return "redirect:/recover_password_3";
	}
	
}
