package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applyandgrowth.models.Product;
import com.applyandgrowth.models.User;
import com.applyandgrowth.repository.ProductRepository;
import com.applyandgrowth.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository pr;
	@Autowired
	private UserRepository ur;
	
	@GetMapping("/myProducts")
	public String products() {
		return "my_products_advertiser";
	}
	
	@GetMapping("/createSale")
	public String sale (){
		return "create_sale";
	}
	
	@PostMapping("/createSale")
	public String createProduct(@Valid Product product, BindingResult br, RedirectAttributes attributes){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = ur.findByEmail(authentication.getName());
		user.setAdvProduct(product);
		product.setUser(user);
		
		if (br.hasErrors())
			return "redirect:/createSale?error";
		else {
			pr.save(product);
			return "redirect:/createSale?success";
		}
	}
}
