
package com.emergentes.modelo;

import java.sql.Blob;


public class Producto {
    private int id;
    private String nombre; 
    private String marca;
    private int stock;
    private double  precio;
    private String estado;
    private Proveedor proveedor;
    private Categoria categoria;
    private Blob blob;

    public Producto() {
    }

    public Producto(int id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
    }

 
    
    

    public Producto(int id, String nombre, String marca, int stock, double precio, String estado, Proveedor proveedor, Categoria categoria, Blob blob) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.blob = blob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
    
    
    
}
