package com.sistemaMarket.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistemaMarket.app.model.Producto;
import com.sistemaMarket.app.service.CategoriaService;
import com.sistemaMarket.app.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
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
                                   @RequestParam String seccion,
                                   @RequestParam String estante,
                                   @RequestParam String nivel,
                                   Model model) {

        Producto productoGuardado = productoService.guardarProducto(producto);

        String prefijoCategoria = obtenerPrefijoCategoria(productoGuardado.getCategoria().getNombre());
        String ubicacion = seccion.toUpperCase() + estante + nivel;
        String idHex = String.format("%04X", productoGuardado.getIdProducto());
        String codigoGenerado = prefijoCategoria + "-" + ubicacion + "-" + idHex;

        productoGuardado.setCodigoInventario(codigoGenerado);
        productoService.actualizarProducto(productoGuardado);

        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        Optional<Producto> productoOpt = productoService.buscarProductoPorId(id);
        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
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
    public String buscar(@RequestParam String tipo,
                         @RequestParam(required = false) String valor,
                         Model model) {

        List<Producto> productos;

        if ("categoria".equals(tipo)) {
            Integer idCategoria = Integer.parseInt(valor);
            productos = productoService.buscarProductosPorCategoria(idCategoria);
        } else if ("codigo".equals(tipo)) {
            productos = productoService.buscarProductoPorCodigo(valor)
                    .map(List::of)
                    .orElse(List.of());
        } else {
            productos = productoService.buscarProductosPorDescripcion(valor);
        }

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/lista";
    }

    private String obtenerPrefijoCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.isBlank()) {
            return "GEN";
        }

        String limpio = nombreCategoria.replaceAll("[^A-Za-z]", "").toUpperCase();
        return limpio.length() >= 3
                ? limpio.substring(0, 3)
                : String.format("%-3s", limpio).replace(' ', 'X');
    }
}