package com.rafael.estudos.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.Categoria;
import com.rafael.estudos.springboot.dto.CategoriaDTO;
import com.rafael.estudos.springboot.exception.DataIntegrityException;
import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public CategoriaDTO find(Integer id) {

		return fromDTO(repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")));
	}

	public CategoriaDTO insert(CategoriaDTO obj) {
		return fromDTO(repository.save(toDomain(obj)));
	}

	public CategoriaDTO update(CategoriaDTO obj) {
		this.find(obj.id);
		return fromDTO(repository.save(toDomain(obj)));
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria com produtos vinculados", e);
		}
	}

	public List<CategoriaDTO> findAll() {
		return repository.findAll().stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

	private CategoriaDTO fromDTO(Categoria cat) {
		return new CategoriaDTO(cat);
	}

	private Categoria toDomain(CategoriaDTO catDto) {

		var categoria = new Categoria();

		categoria.setId(catDto.id);
		categoria.setNome(catDto.nome);
		return categoria;
	}

}
