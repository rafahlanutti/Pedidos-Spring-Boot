package com.rafael.estudos.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.Categoria;
import com.rafael.estudos.springboot.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {

		return repository.findById(id).orElse(null);
	}
}
