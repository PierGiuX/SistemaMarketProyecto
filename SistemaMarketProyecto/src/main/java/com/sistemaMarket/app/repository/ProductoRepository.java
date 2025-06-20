package com.sistemaMarket.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemaMarket.app.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	@Query("SELECT p FROM Producto p WHERE p.categoria.id_categoria = :idCategoria")
    List<Producto> findByCategoriaId(@Param("idCategoria") int idCategoria);
	
    List<Producto> findByCodigoInventarioContainingIgnoreCase(String codigo);
    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);
}
