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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private static String weekDay;

    public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		ExerciseController.weekDay = weekDay;
	}

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
		return "redirect:/customer/worksheet/deleteExercise?success";
	}

    @GetMapping("/customer/worksheet/planWork")
	public String planWork(@RequestParam(value="plus_button") String weekDay) {
		this.setWeekDay(weekDay);

		return "worksheet_plan_work";
	}

	@PostMapping("/customer/worksheet/planWork")
	public String createExercise() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("aoba");

		// User user = ur.findByEmail(authentication.getName());
		// exercise.setUser(user);
		// exercise.setWeekDay(this.getWeekDay());
		// ex.save(exercise);
		return "redirect:/customer/worksheet/planWork?success";
	}

    @GetMapping("/customer/worksheet/editWork")
	public String editWork() {
		
		return "worksheet_edit_work";
	}

	@PostMapping("/customer/worksheet/editWork")
	public String editExercise(@Valid Exercise exercise, BindingResult br, 
	RedirectAttributes attributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (br.hasErrors())
			return "redirect:/customer/worksheet/editWork?error";
		else {
			User user = ur.findByEmail(authentication.getName());
			exercise.setUser(user);
			exercise.setWeekDay(this.getWeekDay());
			ex.save(exercise);
			return "redirect:/customer/worksheet/editWork?success";
		}
	}
}
