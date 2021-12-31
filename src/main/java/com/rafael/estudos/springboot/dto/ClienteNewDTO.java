package com.rafael.estudos.springboot.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public String nome;
	public String email;
	public String cpfOuCnpj;
	public Integer tipo;
	public String logradouro;
	public String numero;
	public String complemento;
	public String bairro;
	public String cep;
	public String telefone1;
	public String telefone2;
	public String telefone3;
	public Integer cidadeId;

	public ClienteNewDTO() {
	}

}
