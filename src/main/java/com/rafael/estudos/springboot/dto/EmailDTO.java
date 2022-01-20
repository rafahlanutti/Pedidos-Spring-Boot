package com.rafael.estudos.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Email obrigatório")
	@Email(message = "Email inválido")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailDTO() {
		super();
	}

	public EmailDTO(String email) {
		super();
		this.email = email;
	};

}
