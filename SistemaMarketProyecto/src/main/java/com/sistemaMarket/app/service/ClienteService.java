package com.sistemaMarket.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaMarket.app.repository.ClienteRepository;
import com.sistemaMarket.app.model.Cliente;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente obtenerClientePorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
		Cliente existente = clienteRepository.findById(id).orElse(null);
		if (existente != null) {
			existente.setNombre(clienteActualizado.getNombre());
			existente.setRuc(clienteActualizado.getRuc());
			existente.setDireccion(clienteActualizado.getDireccion());
			existente.setTelefono(clienteActualizado.getTelefono());
			return clienteRepository.save(existente);
		}
		return null; // O PUEDES LANZAR UNA EXCEPCION PERSONALIZADA SI PREFIERES
	}
}