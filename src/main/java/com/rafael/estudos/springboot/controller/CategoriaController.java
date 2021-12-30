package com.rafael.estudos.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable(value = "id") Integer id) {

		var categoria = service.buscar(id);

		return ResponseEntity.ok().body(categoria);

	}

}
