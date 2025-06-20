package com.sistemaMarket.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaMarket.app.model.Categoria;
import com.sistemaMarket.app.model.Producto;
import com.sistemaMarket.app.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto guardarProductoNuevo(Producto producto, Categoria categoria, String seccion, String estante, String nivel) {
        // Validaciones básicas
        if (categoria == null || seccion == null || estante == null || nivel == null) {
            throw new IllegalArgumentException("Categoría o ubicación inválida");
        }

        producto.setCategoria(categoria);
        producto.setCodigoInventario("TEMP"); // Código temporal mientras obtenemos el ID
        producto = productoRepository.save(producto); // Guardamos para obtener ID autogenerado

        // Construir prefijo de categoría
        String prefijo = switch (categoria.getNombre().toUpperCase()) {
            case "BEBIDAS" -> "BEB";
            case "CONDIMENTOS" -> "CON";
            case "FRUTAS/VERDURAS" -> "FRU";
            case "CARNES" -> "CAR";
            case "PESCADO/MARISCO" -> "PES";
            case "LACTEOS" -> "LAC";
            case "REPOSTERIA" -> "REP";
            case "GRANOS/CEREALES" -> "GRA";
            default -> "XXX";
        };

        // Construir el código de inventario: PREFIJO-UBICACION-IDHEX
        String ubicacion = (seccion + estante + nivel).toUpperCase();
        String idHex = String.format("%04X", producto.getId_producto());
        String codigoFinal = String.format("%s-%s-%s", prefijo, ubicacion, idHex);

        producto.setCodigoInventario(codigoFinal);

        return productoRepository.save(producto); // Guardamos nuevamente con el código real
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizarProducto(Integer id, Producto nuevoProducto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente != null) {
            productoExistente.setCodigoInventario(nuevoProducto.getCodigoInventario());
            productoExistente.setDescripcion(nuevoProducto.getDescripcion());
            productoExistente.setCategoria(nuevoProducto.getCategoria());
            productoExistente.setPrecio(nuevoProducto.getPrecio());
            productoExistente.setStock(nuevoProducto.getStock());

            return productoRepository.save(productoExistente);
        }

        return null;
    }

    public List<Producto> buscarPorCategoria(int idCategoria) {
        return productoRepository.findByCategoriaId(idCategoria);
    }

    public List<Producto> buscarPorCodigoInventario(String codigo) {
        return productoRepository.findByCodigoInventarioContainingIgnoreCase(codigo);
    }

    public List<Producto> buscarPorDescripcion(String descripcion) {
        return productoRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }
}