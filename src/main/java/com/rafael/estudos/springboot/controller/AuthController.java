package com.rafael.estudos.springboot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.estudos.springboot.security.UserSpringSecurity;
import com.rafael.estudos.springboot.service.UserService;
import com.rafael.estudos.springboot.utils.JWTUtils;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private JWTUtils jwtUtils;

	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtils.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
