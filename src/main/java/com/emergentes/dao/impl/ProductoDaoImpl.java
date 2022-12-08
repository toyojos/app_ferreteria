package com.emergentes.dao.impl;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.modelo.Categoria;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Proveedor;
import com.emergentes.utiles.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDAO {

      
    @Override
    public void insert(Producto producto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Producto producto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id_producto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto getById(int id_producto) throws Exception {
        Producto producto = null;
        String sql = "select * from producto where ID_PRODUCTO=?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_producto);
        ResultSet rs = ps.executeQuery();
        producto = new Producto();
        while (rs.next()) {
            producto.setId(rs.getInt("ID_PRODUCTO"));
            producto.setNombre(rs.getString("NOMBRE"));
            producto.setMarca(rs.getString("MARCA"));
            producto.setStock(rs.getInt("STOCK"));
            producto.setPrecio(rs.getDouble("PRECIO"));
            producto.setEstado(rs.getString("ESTADO"));
            producto.setProveedor(new Proveedor());
            producto.setCategoria(new Categoria());
        }
        ConexionDB.desconectar(conn);
        return producto;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = null;
        String sql = "select * from producto";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList<>();
        while (rs.next()) {
            Producto prod = new Producto();
            prod.setId(rs.getInt("ID_PRODUCTO"));
            prod.setNombre(rs.getString("NOMBRE"));
            prod.setMarca(rs.getString("MARCA"));
            prod.setStock(rs.getInt("STOCK"));
            prod.setPrecio(rs.getDouble("PRECIO"));
            prod.setEstado(rs.getString("ESTADO"));
            prod.setProveedor(new Proveedor());
            prod.setCategoria(new Categoria());
            lista.add(prod);
        }
        ConexionDB.desconectar(conn);
        return lista;
    }

}
