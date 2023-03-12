package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applyandgrowth.models.Client;
import com.applyandgrowth.repository.ClientRepository;

import jakarta.validation.Valid;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository cr;
	
	@RequestMapping(value="/createAccount", method=RequestMethod.GET)
	public String createAccount() {
		return "create_account";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	public String createAccount(@Valid Client client, BindingResult br, RedirectAttributes attributes) {
		if(br.hasErrors()) {
	    	attributes.addFlashAttribute("flashMessage", "Check the fields!");
	    	attributes.addFlashAttribute("flashType", "danger");
			return "redirect:/createAccount"; 
	    }
    	attributes.addFlashAttribute("flashMessage", "Registration done successfully");
    	attributes.addFlashAttribute("flashType", "sucess");
		cr.save(client); 
		return "redirect:/createAccount"; 
	}
	
	@RequestMapping(value="/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/recoverPass")
	public String recoverPassword() {
		return "recover_password";
	}
	
	@RequestMapping(value="/indexClient")
	public String indexClient() {
		return "index";
	}
	
	@RequestMapping(value="/indexAdvertiser")
	public String indexAdvertiser() {
		return "index_advertiser";
	}
	
}
