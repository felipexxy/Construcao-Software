package com.applyandgrowth.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L; //atributo estatico  
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	@NotBlank(message="nome não pode ser nulo")
	private String name;
	@NotBlank(message="email não pode ser nulo")
	@Email(message="email inválido")
	private String email;
	@NotBlank(message="senhao não pode ser nulo")
	private String password;
	
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
	


}
