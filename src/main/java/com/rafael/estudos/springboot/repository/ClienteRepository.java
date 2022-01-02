package com.rafael.estudos.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.estudos.springboot.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Spring Data Padrão de nomes, spring detecta que quer uma busca pelo email
	 */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
