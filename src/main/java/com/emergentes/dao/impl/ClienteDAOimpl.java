
package com.emergentes.dao.impl;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.modelo.Cliente;
import com.emergentes.utiles.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOimpl implements ClienteDAO{

    @Override
    public void insert(Cliente cliente) throws Exception {
        String sql = "insert into cliente (ci,nombres,apellidos,direccion,email,celular) values (?,?,?,?,?,?)";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cliente.getCi());
        ps.setString(2, cliente.getNombres());
        ps.setString(3, cliente.getApellidos());
        ps.setString(4, cliente.getDireccion());
        ps.setString(5, cliente.getEmail());
        ps.setString(6, cliente.getCelular());
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        String sql = "update cliente set ci=?,nombres=?,apellidos=?,direccion=?,email=?,celular=? where id_cliente=?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cliente.getCi());
        ps.setString(2, cliente.getNombres());
        ps.setString(3, cliente.getApellidos());
        ps.setString(4, cliente.getDireccion());
        ps.setString(5, cliente.getEmail());
        ps.setString(6, cliente.getCelular());
        ps.setInt(7, cliente.getId_cliente());
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }

    @Override
    public void delete(int id_cliente) throws Exception {
        String sql = "delete from cliente where id_cliente = ?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_cliente);
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }

    @Override
    public Cliente getById(int id_cliente) throws Exception {
        Cliente cli = new Cliente();
        String sql = "select * from cliente where id_cliente = ?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_cliente);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            cli.setId_cliente(rs.getInt("id_cliente"));
            cli.setCi(rs.getString("ci"));
            cli.setNombres(rs.getString("nombres"));
            cli.setApellidos(rs.getString("apellidos"));
            cli.setDireccion(rs.getString("direccion"));
            cli.setEmail(rs.getString("email"));
            cli.setCelular(rs.getString("celular"));
        }
        ConexionDB.desconectar(conn);
        return cli;
    }

    @Override
    public List<Cliente> getAll() throws Exception {
        List<Cliente> lista = null;
        String sql = "select * from cliente";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList<>();
        while (rs.next()) {            
            Cliente cli = new Cliente();
            cli.setId_cliente(rs.getInt("id_cliente"));
            cli.setCi(rs.getString("ci"));
            cli.setNombres(rs.getString("nombres"));
            cli.setApellidos(rs.getString("apellidos"));
            cli.setDireccion(rs.getString("direccion"));
            cli.setEmail(rs.getString("email"));
            cli.setCelular(rs.getString("celular"));
            lista.add(cli);
        }
        ConexionDB.desconectar(conn);
        return lista;
    }
    
}
