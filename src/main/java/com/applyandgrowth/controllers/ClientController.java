package com.applyandgrowth.controllers;

import java.sql.*;  

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
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String login(@Valid Client client, BindingResult br, RedirectAttributes attributes) {
		String validationResult = this.authenticateUser(client.getEmail(), client.getPassword());

		if(validationResult.equals("0")) {
			return "redirect:/indexClient";
		} else if(validationResult.equals("1")) {
			return "redirect:/indexAdvertiser";
		}
		
		attributes.addFlashAttribute("flashMessage", "Wrong email or password!");
		attributes.addFlashAttribute("flashType", "danger");
		return "redirect:/"; 
	}

	private String authenticateUser(String email, String password) {
		String authenticationResult;

		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/applyandgrowth", "root", "2358");  
			Statement stmt = con.createStatement();  

			String authenticationQuery = "select * from client where email = '" +
				email + "' and password = '" + password + "'";
			System.out.println(authenticationQuery);

			ResultSet rs=stmt.executeQuery(authenticationQuery);  

			if(rs.next()) {
				authenticationResult =  rs.getString(4);  // valor de isAdvertiser
			} else {
				authenticationResult = "false";
			}

			rs.close();
			
			return authenticationResult;
		} catch(Exception e) {
			System.out.println("Erro: " + e);
			System.exit(1);
		}

		return "exception";
	}
	
	@RequestMapping(value="/recoverPass", method=RequestMethod.GET)
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
