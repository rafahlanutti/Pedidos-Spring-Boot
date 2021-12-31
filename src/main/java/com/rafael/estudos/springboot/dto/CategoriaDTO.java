package com.rafael.estudos.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafael.estudos.springboot.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public Integer id;

	@NotEmpty(message = "Não pode ser vazio")
	@Length(min = 5, max = 100, message = "Minimo de 5 maximo de 100")
	public String nome;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

}
