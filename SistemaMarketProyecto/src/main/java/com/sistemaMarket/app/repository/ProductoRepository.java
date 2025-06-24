package com.sistemaMarket.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaMarket.app.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	// BUSCAR POR ID DE CATEGORIA
    List<Producto> findByCategoria_IdCategoria(Integer idCategoria);

    // BUSCAR POR CODIGO DE INVENTARIO EXACTO
    Optional<Producto> findByCodigoInventario(String codigoInventario);

    // BUSCAR POR DESCRIPCION QUE CONTENGA TEXTO
    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);
}
