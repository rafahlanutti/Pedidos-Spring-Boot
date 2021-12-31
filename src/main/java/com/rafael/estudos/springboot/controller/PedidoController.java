package com.rafael.estudos.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.domain.Pedido;
import com.rafael.estudos.springboot.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	PedidoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable(value = "id") Integer id) {

		var pedido = service.find(id);

		return ResponseEntity.ok().body(pedido);

	}

}
