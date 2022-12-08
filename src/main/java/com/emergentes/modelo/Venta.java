
package com.emergentes.modelo;

import java.sql.Date;
import java.util.List;

public class Venta {
    private int id;
    private Cliente cliente;
    private Usuario usuario;
    private String numeroVenta;
    private Date fechaVenta;
    private double  montoVenta;
    private double subTotal;
    private double iva;
    private List<DetalleVenta> detalleVenta;
            
    public Venta() {
    }

    public Venta(int id, Cliente cliente, Usuario usuario, String numeroVenta, Date fechaVenta, double montoVenta, double subTotal, double iva, List<DetalleVenta> detalleVenta) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.numeroVenta = numeroVenta;
        this.fechaVenta = fechaVenta;
        this.montoVenta = montoVenta;
        this.subTotal = subTotal;
        this.iva = iva;
        this.detalleVenta = detalleVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getMontoVenta() {
        for (DetalleVenta detalle : detalleVenta) {
            montoVenta=montoVenta+detalle.getPrecioTotal();
        }
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    public double getSubTotal() {
        subTotal=Math.round(montoVenta/1.18);
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIva() {
        iva=Math.round(montoVenta*0.18);
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    
    
}
