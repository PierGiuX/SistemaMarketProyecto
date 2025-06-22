package com.sistemaMarket.app.controller;

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
	private final CategoriaService categoriaService;
	
	public CategoriaController (CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping
	public String listarCategorias(Model model) {
		model.addAttribute("categorias", categoriaService.listarCategorias());
		return "categorias/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevaCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categorias/formulario";
	}
	
	@PostMapping("/guardar")
	public String guardarCategoria(@ModelAttribute Categoria categoria) {
		if (categoria.getId_categoria() != null) {
			// SI TIENE ID, ACTUALIZA
			categoriaService.actualizarCategoria(categoria.getId_categoria(), categoria);
		} else {
			// SI NO TIENE ID, ES NUEVO
			categoriaService.guardarCategoria(categoria);
		}
		return "redirect:/categorias";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCategoria(@PathVariable Integer id) {
		categoriaService.eliminarCategoria(id);
		return "redirect:/categorias";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable Integer id, Model model) {
		Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
		if(categoria != null) {
			model.addAttribute("categoria", categoria);
			return "categorias/formulario"; //REUTILIZA EL MISMO FORMULARIO DE REGISTRO
		} else {
			return "redirect:/categorias"; // O MOSTRAR UNA PAGINA DE ERROR (NO CODIFICADO / NO HECHO)
		}
	}
	
}
