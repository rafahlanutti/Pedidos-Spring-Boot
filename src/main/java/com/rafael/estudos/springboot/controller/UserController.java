package com.rafael.estudos.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.domain.User;
import com.rafael.estudos.springboot.service.UserMongoDBService;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

	@Autowired
	private UserMongoDBService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
}
