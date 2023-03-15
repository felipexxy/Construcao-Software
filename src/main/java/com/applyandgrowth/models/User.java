package com.applyandgrowth.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String password;

	@NotBlank
	private String isAdvertiser;
	
	private boolean activated = false;

	public User() {
		
	}

	public User(
		String name,
		String email,
		String password,
		String isAdvertiser
	) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.isAdvertiser = isAdvertiser;
	}
	public String getIsAdvertiser() {
		return isAdvertiser;
	}
	public void setIsAdvertiser(String isAdvertiser) {
		this.isAdvertiser = isAdvertiser;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
