package com.sistemaMarket.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaMarket.app.model.Producto;
import com.sistemaMarket.app.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    // METODOS PERSONALIZADOS

    public List<Producto> buscarProductosPorCategoria(Integer idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    public Optional<Producto> buscarProductoPorCodigo(String codigoInventario) {
        return productoRepository.findByCodigoInventario(codigoInventario);
    }

    public List<Producto> buscarProductosPorDescripcion(String descripcion) {
        return productoRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }
}