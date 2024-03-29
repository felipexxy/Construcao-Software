package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

import com.applyandgrowth.models.User;
import com.applyandgrowth.security.UserDto;
import com.applyandgrowth.security.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.applyandgrowth.models.Product;
import com.applyandgrowth.models.User;
import com.applyandgrowth.repository.ProductRepository;
import com.applyandgrowth.security.UserDto;
import com.applyandgrowth.security.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {

    private UserService userService;

	@Autowired
    private ProductRepository pr;

    private UserService er;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/customer")
    public ModelAndView indexCustomer(){
		ModelAndView mv = new ModelAndView("index");
        Iterable<Product> products = pr.findAll();
		mv.addObject("products", products);
		return mv;
    }


    @GetMapping("/advertiser")
    public ModelAndView indexAdvertiser(){
		ModelAndView mv = new ModelAndView("index_advertiser");
        Iterable<Product> products = pr.findAll();
		mv.addObject("products", products);
		return mv;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "redirect:/register?error";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }

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