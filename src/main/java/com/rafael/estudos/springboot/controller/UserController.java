package com.rafael.estudos.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.domain.User;
import com.rafael.estudos.springboot.dto.UserDTO;
import com.rafael.estudos.springboot.service.UserMongoDBService;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

	@Autowired
	private UserMongoDBService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		var usersDTO = service.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		var user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));

	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO obj) {
		var user = new User(obj);
		return ResponseEntity.ok().body(new UserDTO(service.insert(user)));
	}
}
