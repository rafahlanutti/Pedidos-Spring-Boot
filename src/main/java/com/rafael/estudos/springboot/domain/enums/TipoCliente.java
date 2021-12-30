package com.rafael.estudos.springboot.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa física"), PESSOAJURIDICA(2, "Pessoa Juridica");

	private Integer codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente t : TipoCliente.values()) {
			if (cod.equals(t.codigo)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Tipo inválido" + cod);

	}
}
