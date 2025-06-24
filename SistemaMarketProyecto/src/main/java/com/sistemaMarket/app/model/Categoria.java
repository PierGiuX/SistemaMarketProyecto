package com.sistemaMarket.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categoria")
    private Integer idCategoria;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Producto> productos;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // ✅ Getters y Setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}