package com.sistemaMarket.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaMarket.app.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
