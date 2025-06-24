package com.sistemaMarket.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaMarket.app.model.Categoria;
import com.sistemaMarket.app.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
