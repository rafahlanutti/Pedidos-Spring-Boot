package com.rafael.estudos.springboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.estudos.springboot.domain.Categoria;
import com.rafael.estudos.springboot.domain.Cidade;
import com.rafael.estudos.springboot.domain.Cliente;
import com.rafael.estudos.springboot.domain.Endereco;
import com.rafael.estudos.springboot.domain.Estado;
import com.rafael.estudos.springboot.domain.Pagamento;
import com.rafael.estudos.springboot.domain.PagamentoComBoleto;
import com.rafael.estudos.springboot.domain.PagamentoComCartao;
import com.rafael.estudos.springboot.domain.Pedido;
import com.rafael.estudos.springboot.domain.Produto;
import com.rafael.estudos.springboot.domain.enums.EstadoPagamento;
import com.rafael.estudos.springboot.domain.enums.TipoCliente;
import com.rafael.estudos.springboot.repository.CategoriaRepository;
import com.rafael.estudos.springboot.repository.CidadeRepository;
import com.rafael.estudos.springboot.repository.ClienteRepository;
import com.rafael.estudos.springboot.repository.EnderecoRepository;
import com.rafael.estudos.springboot.repository.EstadoRepository;
import com.rafael.estudos.springboot.repository.PagamentoRepository;
import com.rafael.estudos.springboot.repository.PedidoRepository;
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

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

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

		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "391239123", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("123123123123", "2739123891273"));

		Endereco end1 = new Endereco(null, "Rua legal", "300", "Apto 20", "Jardim", "2139123", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "300", "Apto 20", "Jardim", "2139123", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
