package com.rafael.estudos.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.domain.User;
import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.UserRepository;

@Service
public class UserMongoDBService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {

		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public User update(String id, User obj) {
		this.findById(id);
		return repository.save(obj);
	}

	public void delete(String id) {
		this.findById(id);
		repository.deleteById(id);
	}
}
