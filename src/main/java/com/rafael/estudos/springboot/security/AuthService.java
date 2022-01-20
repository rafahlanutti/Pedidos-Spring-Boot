package com.rafael.estudos.springboot.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafael.estudos.springboot.exception.ObjectNotFoundException;
import com.rafael.estudos.springboot.repository.ClienteRepository;
import com.rafael.estudos.springboot.service.EmailService;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private SecureRandom random = new SecureRandom();

	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public void sendNewPassword(String email) {

		var cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		var newPassword = generateRandomPassword(10);
		cliente.setSenha(bCryptPasswordEncoder.encode(email));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);

	}

	private String generateRandomPassword(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(CHARS.length());
			sb.append(CHARS.charAt(randomIndex));
		}
		return sb.toString();
	}
}
