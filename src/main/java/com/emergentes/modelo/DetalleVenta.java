
package com.emergentes.modelo;

import java.text.DecimalFormat;

public class DetalleVenta {
    private int id;
    private Producto producto;
    private int cantidad;
    private double precioVenta;
    private String precioVentaf;
    private String precioTotalf;
    private double precioTotal;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    
    public DetalleVenta() {
    }

    public DetalleVenta(int id, Producto producto, int cantidad, double precioVenta) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioTotal() {
        precioTotal = cantidad*precioVenta;
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getPrecioVentaf() {
        precioVentaf=DECIMAL_FORMAT.format(precioVenta);
        return precioVentaf;
    }

    public void setPrecioVentaf(String precioVentaf) {
        this.precioVentaf = precioVentaf;
    }

    public String getPrecioTotalf() {
        precioTotalf=DECIMAL_FORMAT.format(cantidad*precioVenta);
        return precioTotalf;
    }

    public void setPrecioTotalf(String precioTotalf) {
        this.precioTotalf = precioTotalf;
    }
    
    
}
