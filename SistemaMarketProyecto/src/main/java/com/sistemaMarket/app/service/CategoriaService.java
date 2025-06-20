package com.sistemaMarket.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaMarket.app.model.Categoria;
import com.sistemaMarket.app.repository.CategoriaRepository;

@Service
public class CategoriaService {
	private final CategoriaRepository categoriaRepository;
	
	public CategoriaService (CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<Categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Categoria obtenerCategoriaPorId(int id) {
		return categoriaRepository.findById(id).orElse(null);
	}
}
