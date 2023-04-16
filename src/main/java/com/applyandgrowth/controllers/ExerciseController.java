package com.applyandgrowth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.applyandgrowth.models.Exercise;
import com.applyandgrowth.models.User;
import com.applyandgrowth.repository.ExerciseRepository;
import com.applyandgrowth.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class ExerciseController {
	@Autowired
	private ExerciseRepository ex;

	@Autowired
	private UserRepository ur;

	@GetMapping("/customer/worksheet")
	public ModelAndView worksheet() {
		ModelAndView mv = new ModelAndView("worksheet");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = ur.findByEmail(authentication.getName());
		Iterable<Exercise> exercises = ex.findByUser_id(user.getId());
		mv.addObject("exercises", exercises);
		return mv;
	}

	@GetMapping("/customer/worksheet/deleteExercise")
	public String deleteExercise(@RequestParam int id) {
		Exercise exercise = ex.findById(id);
		ex.delete(exercise);
		return "redirect:/customer/worksheet";
	}

    @GetMapping("/customer/worksheet/planExercise")
	public String createExerciseGet() {
		return "worksheet_plan_work";
	}

	@PostMapping("/customer/worksheet/planExercise")
	public String createExercisePost(@RequestParam(value="plus_button") 
	String weekDay, @Valid Exercise exercise, BindingResult br) {
		exercise.setWeekDay(weekDay);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (br.hasErrors() || !isPositiveInt(exercise.getSets()) || !isPositiveInt(exercise.getReps()) || !isPositiveInt(exercise.getWeight()))
			return "redirect:/customer/worksheet/planExercise?error" + "&plus_button=" + weekDay;
		else {
			User user = ur.findByEmail(authentication.getName());
			exercise.setUser(user);
			ex.save(exercise);
			return "redirect:/customer/worksheet";
		}
	}

    @GetMapping("/customer/worksheet/editExercise")
	public String editExerciseGet() {
		return "worksheet_edit_work";
	}

	@PostMapping("/customer/worksheet/editExercise")
	public String editExercisePost(@RequestParam int id, @Valid Exercise exercise, BindingResult br) {
		if (br.hasErrors() || !isPositiveInt(exercise.getSets()) || !isPositiveInt(exercise.getReps()) || !isPositiveInt(exercise.getWeight()))
			return "redirect:/customer/worksheet/editExercise?error"  + "&id=" + id;
		else {
			Exercise exerciseSearched = ex.findById(id);
			exerciseSearched.setName(exercise.getName());
			exerciseSearched.setSets(exercise.getSets());
			exerciseSearched.setReps(exercise.getReps());
			exerciseSearched.setWeight(exercise.getWeight());
			ex.save(exerciseSearched);
			return "redirect:/customer/worksheet";
		}
	}

	public static boolean isPositiveInt(String str) { 
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
