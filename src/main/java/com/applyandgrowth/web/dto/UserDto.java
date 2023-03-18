package com.applyandgrowth.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotEmpty(message = "Full name should not be empty")
	private String name;
	@NotEmpty(message = "Email should not be empty")
    @Email
	private String email;
	@NotEmpty(message = "Password should not be empty")
	private String password;
	@NotEmpty
	private String role;
	private boolean activated = false;
	@NotEmpty(message = "Please agree with terms and conditions")
	private String terms;
}
