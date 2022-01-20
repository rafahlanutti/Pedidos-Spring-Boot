package com.rafael.estudos.springboot.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.rafael.estudos.springboot.security.UserSpringSecurity;

public class UserService {

	private UserService() {
		super();
		// makes sonar happy
	}

	public static UserSpringSecurity authenticated() {
		try {

			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
