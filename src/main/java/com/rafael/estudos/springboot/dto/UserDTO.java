package com.rafael.estudos.springboot.dto;

import java.io.Serializable;

import com.rafael.estudos.springboot.domain.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String email;
	private String name;

	public UserDTO(String id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}

	public UserDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

}
