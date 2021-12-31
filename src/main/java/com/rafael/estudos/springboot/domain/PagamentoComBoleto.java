package com.rafael.estudos.springboot.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.estudos.springboot.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto() {
		super();
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento,
			Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataPagamento, dataVencimento);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoComBoleto other = (PagamentoComBoleto) obj;
		return Objects.equals(dataPagamento, other.dataPagamento)
				&& Objects.equals(dataVencimento, other.dataVencimento);
	}

}
