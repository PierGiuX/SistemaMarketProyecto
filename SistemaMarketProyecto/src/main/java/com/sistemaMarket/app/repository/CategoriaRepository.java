package com.sistemaMarket.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaMarket.app.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{
	
}
