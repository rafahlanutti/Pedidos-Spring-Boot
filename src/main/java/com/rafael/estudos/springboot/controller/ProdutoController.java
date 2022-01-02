package com.rafael.estudos.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.domain.Produto;
import com.rafael.estudos.springboot.dto.ProdutoDTO;
import com.rafael.estudos.springboot.service.ProdutoService;
import com.rafael.estudos.springboot.utils.URL;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable(value = "id") Integer id) {
		var pedido = service.find(id);

		return ResponseEntity.ok().body(pedido);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decoreParam(nome);
		List<Integer> ids = URL.decoreIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
