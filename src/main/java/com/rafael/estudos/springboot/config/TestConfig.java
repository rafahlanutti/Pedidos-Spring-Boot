package com.rafael.estudos.springboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafael.estudos.springboot.service.DbService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService service;
	

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		service.instantiateDataBase();
		return true;
	}

}
