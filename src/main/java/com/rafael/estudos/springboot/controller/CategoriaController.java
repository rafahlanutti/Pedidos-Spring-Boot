package com.rafael.estudos.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.estudos.springboot.dto.CategoriaDTO;
import com.rafael.estudos.springboot.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> find(@PathVariable(value = "id") Integer id) {

		var categoria = service.find(id);

		return ResponseEntity.ok().body(categoria);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO obj) {

		obj = service.insert(obj);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.id).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @PathVariable(value = "id") Integer id, @RequestBody CategoriaDTO obj) {

		obj.id = id;
		this.service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {
		this.service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());

	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		var categorias = this.service.findPage(page, linesPerPage, direction, orderBy);
		var categoriasDto = categorias.map(cat -> new CategoriaDTO(cat));

		return ResponseEntity.ok(categoriasDto);

	}

}
