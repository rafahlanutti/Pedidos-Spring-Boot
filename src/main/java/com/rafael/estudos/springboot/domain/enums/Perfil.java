package com.rafael.estudos.springboot.domain.enums;

public enum Perfil {

	ADMIN(1, "HOLE_ADMIN"), CLIENTE(2, "HOLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil t : Perfil.values()) {
			if (cod.equals(t.codigo)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Tipo inválido" + cod);

	}
}
