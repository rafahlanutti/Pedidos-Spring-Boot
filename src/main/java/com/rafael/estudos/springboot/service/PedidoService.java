package com.rafael.estudos.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.Pedido;
import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido buscar(Integer id) {

		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
