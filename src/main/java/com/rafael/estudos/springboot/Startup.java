package com.rafael.estudos.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.estudos.springboot.domain.Categoria;
import com.rafael.estudos.springboot.domain.Cidade;
import com.rafael.estudos.springboot.domain.Estado;
import com.rafael.estudos.springboot.domain.Produto;
import com.rafael.estudos.springboot.repository.CategoriaRepository;
import com.rafael.estudos.springboot.repository.CidadeRepository;
import com.rafael.estudos.springboot.repository.EstadoRepository;
import com.rafael.estudos.springboot.repository.ProdutoRepository;

@SpringBootApplication
public class Startup implements CommandLineRunner {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EstadoRepository estadoRepository;

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

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlandia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

	}

}
