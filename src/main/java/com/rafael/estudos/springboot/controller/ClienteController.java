package com.rafael.estudos.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable(value = "id") Integer id) {

		var cliente = service.buscar(id);

		return ResponseEntity.ok().body(cliente);

	}

}
