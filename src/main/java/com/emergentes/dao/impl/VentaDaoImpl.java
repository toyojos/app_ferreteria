package com.emergentes.dao.impl;

import com.emergentes.dao.VentaDAO;
import com.emergentes.modelo.DetalleVenta;
import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import com.emergentes.utiles.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class VentaDaoImpl implements VentaDAO {

    @Override
    public int insert(Venta venta) throws Exception {
        String numeroVenta = "0";
        String sql = "select max(NUMERO_VENTAS) from ventas";
        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            numeroVenta = rs.getString(1);
        }
        venta.setNumeroVenta(Serie.getSerie(numeroVenta));

        sql = "INSERT INTO ventas (ID_CLIENTE, ID_USUARIO, NUMERO_VENTAS, FECHA_VENTAS, PRECIO_TOTAL) VALUES (?,?,?,?,?)";

        int id_venta = 0;
        ps = conn.prepareStatement(sql);
        ps.setInt(1, venta.getCliente().getId_cliente());
        ps.setInt(2, venta.getUsuario().getId_usuario());
        ps.setString(3, venta.getNumeroVenta());
        ps.setDate(4, venta.getFechaVenta());
        ps.setDouble(5, venta.getMontoVenta());
        ps.executeUpdate();

        sql = "SELECT LAST_INSERT_ID()";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            id_venta = rs.getInt(1);
        }
        for (DetalleVenta detalleVenta : venta.getDetalleVenta()) {
            sql = "INSERT INTO detalle_ventas (ID_VENTAS, ID_PRODUCTO, CANTIDAD, PRECIO_VENTA) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_venta);
            ps.setInt(2, detalleVenta.getProducto().getId());
            ps.setInt(3, detalleVenta.getCantidad());
            ps.setDouble(4, detalleVenta.getPrecioVenta());
            ps.executeUpdate();
        }

        ConexionDB.desconectar(conn);
        return id_venta;
    }

    @Override
    public void update(Venta venta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id_venta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta getById(int id_venta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public static void main(String[] args) throws Exception {
//        VentaDaoImpl daoImpl = new VentaDaoImpl();
//        System.out.println(daoImpl.getLastIdNum());
//    }
    @Override
    public String getLastIdNum() throws Exception {
        String numeroVenta = "0";
        String sql = "select max(NUMERO_VENTAS) from ventas";
        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            numeroVenta = rs.getString(1);
        }
        ConexionDB.desconectar(conn);
        return numeroVenta;
    }

}
