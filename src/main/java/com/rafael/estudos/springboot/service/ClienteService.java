package com.rafael.estudos.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.Cidade;
import com.rafael.estudos.springboot.domain.Cliente;
import com.rafael.estudos.springboot.domain.Endereco;
import com.rafael.estudos.springboot.domain.enums.TipoCliente;
import com.rafael.estudos.springboot.dto.ClienteDTO;
import com.rafael.estudos.springboot.dto.ClienteNewDTO;
import com.rafael.estudos.springboot.exception.DataIntegrityException;
import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.ClienteRepository;
import com.rafael.estudos.springboot.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.id, objDto.nome, objDto.email, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.nome, objDto.email, objDto.cpfOuCnpj, TipoCliente.toEnum(objDto.tipo));
		Cidade cid = new Cidade(objDto.cidadeId, null, null);
		Endereco end = new Endereco(null, objDto.logradouro, objDto.numero, objDto.complemento, objDto.bairro,
				objDto.cep, cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.telefone1);
		if (objDto.telefone2 != null) {
			cli.getTelefones().add(objDto.telefone2);
		}
		if (objDto.telefone3 != null) {
			cli.getTelefones().add(objDto.telefone3);
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
