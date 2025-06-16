package com.sistemaMarket.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemaMarket.app.model.Cliente;
import com.sistemaMarket.app.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public String listarClientes(Model model) {
		model.addAttribute("clientes", clienteService.listarClientes());
		return "clientes/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clientes/formulario";
	}
	
	@PostMapping("/guardar")
	public String guardarCliente(@ModelAttribute Cliente cliente) {
		if (cliente.getId_cliente() != null) {
			// SI TIENE ID, ACUTALIZA
			clienteService.actualizarCliente(cliente.getId_cliente(), cliente);
		} else {
			// SI NO TIENE ID, ES NUEVO
			clienteService.guardarCliente(cliente);
		}
		return "redirect:/clientes";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCliente(@PathVariable Long id) {
		clienteService.eliminarCliente(id);
		return "redirect:/clientes";
	}
	
	@GetMapping("/editar/{id}")
	public String EditarCliente(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.obtenerClientePorId(id);
		if (cliente != null) {
			model.addAttribute("cliente", cliente);
			return "clientes/formulario"; //REUTILIZA EL MISMO FORMULARIO DE REGISTRO
		} else {
			return "redirect:/clientes"; // O MOSTRAR UNA PAGINA DE ERROR (NO CODIFICADO / NO HECHO)
		}
	}
}
