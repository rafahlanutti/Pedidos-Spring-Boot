package com.rafael.estudos.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.estudos.springboot.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
