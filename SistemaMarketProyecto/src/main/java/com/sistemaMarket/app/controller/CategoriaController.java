package com.sistemaMarket.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemaMarket.app.model.Categoria;
import com.sistemaMarket.app.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "categorias/lista";
    }

    @GetMapping("/nueva")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Optional<Categoria> categoriaOpt = categoriaService.buscarCategoriaPorId(id);
        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
            return "categorias/formulario";
        } else {
            return "redirect:/categorias";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
}