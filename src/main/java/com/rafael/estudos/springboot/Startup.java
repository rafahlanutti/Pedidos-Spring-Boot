package com.rafael.estudos.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class Startup implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
