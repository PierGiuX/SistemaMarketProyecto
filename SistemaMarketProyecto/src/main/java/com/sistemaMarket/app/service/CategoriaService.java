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
	
	public Categoria guardarCategoria (Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void eliminarCategoria (Integer id) {
		categoriaRepository.deleteById(id);
	}
	
	public Categoria actualizarCategoria(Integer id, Categoria categoriaActualizada) {
		Categoria existente = categoriaRepository.findById(id).orElse(null);
		if (existente != null) {
			existente.setNombre(categoriaActualizada.getNombre());
			return categoriaRepository.save(existente);
		}
		return null; // O PUEDES LANZAR UNA EXCEPCION PERSONALIZADA SI PREFIERES
	}
}
