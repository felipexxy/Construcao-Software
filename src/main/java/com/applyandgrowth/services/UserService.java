package com.applyandgrowth.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.applyandgrowth.models.User;
import com.applyandgrowth.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
