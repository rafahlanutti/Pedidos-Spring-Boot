package com.rafael.estudos.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.estudos.springboot.domain.Categoria;
import com.rafael.estudos.springboot.domain.Produto;
import com.rafael.estudos.springboot.repository.CategoriaRepository;
import com.rafael.estudos.springboot.repository.ProdutoRepository;

@SpringBootApplication
public class Startup implements CommandLineRunner {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria c1 = new Categoria(null, "INFORMATICA");
		Categoria c2 = new Categoria(null, "ESCRITÓRIO");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
