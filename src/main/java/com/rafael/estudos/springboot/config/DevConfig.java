package com.rafael.estudos.springboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafael.estudos.springboot.service.DbService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DbService service;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		if (strategy.equals("create")) {
			service.instantiateDataBase();

		}
		return true;
	}

}
