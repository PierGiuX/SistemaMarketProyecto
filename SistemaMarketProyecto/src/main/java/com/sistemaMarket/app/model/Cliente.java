package com.sistemaMarket.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private Long id_cliente;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "Ruc", length = 12)
    private String ruc;
    
    @Column(name = "Direccion", length = 100)
    private String direccion;
    
    @Column(name = "Telefono", length = 20)
    private String telefono;
    
    public Cliente () {
    
    }

	public Cliente(Long id_cliente, String nombre, String ruc, String direccion, String telefono) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
}