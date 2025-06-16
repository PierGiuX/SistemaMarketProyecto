package com.sistemaMarket.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaMarket.app.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
