package com.sistemaMarket.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemaMarket.app.model.Categoria;
import com.sistemaMarket.app.model.Producto;
import com.sistemaMarket.app.service.CategoriaService;
import com.sistemaMarket.app.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  @RequestParam(name = "categoria.id_categoria") Integer idCategoria,
                                  @RequestParam(name = "seccion", required = false) String seccion,
                                  @RequestParam(name = "estante", required = false) String estante,
                                  @RequestParam(name = "nivel", required = false) String nivel) {

        Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);

        if (producto.getId_producto() != null && producto.getId_producto() != 0) {
            // Edición de producto existente
            Producto existente = productoService.obtenerProductoPorId(producto.getId_producto());
            if (existente != null) {
                existente.setDescripcion(producto.getDescripcion());
                existente.setPrecio(producto.getPrecio());
                existente.setStock(producto.getStock());
                existente.setCategoria(categoria);
                productoService.guardarProducto(existente);
            }
        } else {
            // Registro de nuevo producto (requiere ubicación)
            productoService.guardarProductoNuevo(producto, categoria, seccion, estante, nivel);
        }

        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "productos/formulario";
        } else {
            return "redirect:/productos";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }

    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam("criterio") String criterio,
                                  @RequestParam("valor") String valor,
                                  Model model) {
        List<Producto> productos;

        switch (criterio) {
            case "categoria":
                try {
                    int idCategoria = Integer.parseInt(valor);
                    productos = productoService.buscarPorCategoria(idCategoria);
                } catch (NumberFormatException e) {
                    productos = List.of();
                }
                break;
            case "codigo":
                productos = productoService.buscarPorCodigoInventario(valor);
                break;
            case "descripcion":
                productos = productoService.buscarPorDescripcion(valor);
                break;
            default:
                productos = productoService.listarProductos();
                break;
        }

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/lista";
    }
}