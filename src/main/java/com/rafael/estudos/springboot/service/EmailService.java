package com.rafael.estudos.springboot.service;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.estudos.springboot.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

}
