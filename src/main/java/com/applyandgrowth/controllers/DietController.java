package com.applyandgrowth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.applyandgrowth.models.User;
import com.applyandgrowth.repository.UserRepository;
import com.applyandgrowth.util.DietFactory;
import com.applyandgrowth.util.DietProfile;
import com.applyandgrowth.util.DietFactory.Meal;

import jakarta.validation.Valid;

@Controller
public class DietController {
    @Autowired
	private UserRepository ur;
    
    @GetMapping("/customer/diet")
	public ModelAndView diet() {
		ModelAndView mv = new ModelAndView("dietsheet");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = ur.findByEmail(authentication.getName());
		List<Meal> meals = null;

		if(user.getDietType() == null) {
			mv.addObject("meals", null);
			return mv;
		}

		if(user.getDietType().equals("1")) {
			meals = DietFactory.makeDiet1();
		} else if(user.getDietType().equals("2")) {
			meals = DietFactory.makeDiet2();
		} else if(user.getDietType().equals("3")) {
			meals = DietFactory.makeDiet3();
		}

		// Iterable<Meal> mealsIterable = meals;

		// for(Meal x: mealsIterable) {
		// 	System.out.println(x.getName());
		// }

		mv.addObject("meals", meals);
		return mv;
	}

	@GetMapping("/customer/diet/create")
	public String createDietGet() {
		return "dietsheet_create";
	}

	@PostMapping("/customer/diet/create")
	public String createDietPost(@Valid DietProfile dietProfile, BindingResult br) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (br.hasErrors() || !isPositiveNumeric(dietProfile.getHeight()) || !isPositiveNumeric(dietProfile.getWeight()))
			return "redirect:/customer/diet/create?error";
		else {
			String dietType = null;
			Float weightFloat = Float.parseFloat(dietProfile.getWeight());
			Float heightFloat = Float.parseFloat(dietProfile.getHeight());

			Float imc = weightFloat/(heightFloat*heightFloat);

			if(imc < 18.5) {  // Dieta para abaixo do peso
				dietType = "1";
			} else if(imc > 24.9) {  // Dieta para abaixo do peso
				dietType = "2";
			} else {  // Dieta para peso normal
				dietType = "3";
			}

			User user = ur.findByEmail(authentication.getName());
			user.setDietType(dietType);
			ur.save(user);

			return "redirect:/customer/diet";
		}
	}

	public static boolean isPositiveNumeric(String str) { 
		try {  
			if(Double.parseDouble(str) < 0) {
				return false;
			}  
		} catch(NumberFormatException e){  
		  	return false;  
		}  

		return true;
	  }
}
