package com.applyandgrowth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.applyandgrowth.services.UserService;
import com.applyandgrowth.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService clientService;

	public UserRegistrationController(UserService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerClientAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		clientService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
