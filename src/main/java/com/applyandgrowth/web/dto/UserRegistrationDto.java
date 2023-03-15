package com.applyandgrowth.web.dto;

public class UserRegistrationDto {
	private String name;
	private String email;
	private String password;
	private String role;
	private boolean activated = false;

	public UserRegistrationDto() {
		
	}

	public UserRegistrationDto(String name, String email, String password, String role, boolean activated) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.activated = activated;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
