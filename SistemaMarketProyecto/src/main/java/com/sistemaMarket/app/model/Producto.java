package com.sistemaMarket.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private Integer idProducto;

    @Column(name = "Codigo_inventario", length = 12)
    private String codigoInventario;

    @Column(name = "Descripcion", nullable = false, length = 100)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Categoria_ID")
    private Categoria categoria;

    @Column(name = "Precio", nullable = false, precision = 11, scale = 2)
    private BigDecimal precio;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    public Producto() {
    }

    public Producto(String codigoInventario, String descripcion, Categoria categoria, BigDecimal precio, Integer stock) {
        this.codigoInventario = codigoInventario;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
    	if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }
}