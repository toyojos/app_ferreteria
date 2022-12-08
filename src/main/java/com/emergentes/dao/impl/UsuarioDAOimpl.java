package com.emergentes.dao.impl;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl implements UsuarioDAO {
    
    @Override
    public void insert(Usuario usr) throws Exception {
        String sql = "insert into usuario (ci,nombres,apellidos,direccion,celular,email,contrasena) values (?,?,?,?,?,?,md5(?))";
        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usr.getCi());
        ps.setString(2, usr.getNombres());
        ps.setString(3, usr.getApellidos());
        ps.setString(4, usr.getDireccion());
        ps.setString(5, usr.getCalular());
        ps.setString(6, usr.getEmail());
        ps.setString(7, usr.getContrasena());
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }
    
    @Override
    public void update(Usuario usr) throws Exception {
        String sql;
        if (usr.getContrasena().equals("")) {
            sql = "update usuario set ci=?, nombres=?, apellidos=?, direccion=?, celular=?, email=? where id_usuario=?";
        } else {
            sql = "update usuario set ci=?, nombres=?, apellidos=?, direccion=?, celular=?, email=?, contrasena=md5(?) where id_usuario=?";
        }
        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        if (usr.getContrasena().equals("")) {
            ps.setString(1, usr.getCi());
            ps.setString(2, usr.getNombres());
            ps.setString(3, usr.getApellidos());
            ps.setString(4, usr.getDireccion());
            ps.setString(5, usr.getCalular());
            ps.setString(6, usr.getEmail());
            
            ps.setInt(7, usr.getId_usuario());
        } else {
            ps.setString(1, usr.getCi());
            ps.setString(2, usr.getNombres());
            ps.setString(3, usr.getApellidos());
            ps.setString(4, usr.getDireccion());
            ps.setString(5, usr.getCalular());
            ps.setString(6, usr.getEmail());
            
            ps.setInt(7, usr.getId_usuario());
        }
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }
    
    @Override
    public void delete(int id_usuario) throws Exception {
        String sql = "delete from usuario where id_usuario = ?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_usuario);
        ps.executeUpdate();
        ConexionDB.desconectar(conn);
    }
    
    @Override
    public Usuario getById(int id_usuario) throws Exception {
        Usuario usr = new Usuario();
        String sql = "select * from usuario where id_usuario = ?";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_usuario);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {            
            usr.setId_usuario(rs.getInt("id_usuario"));
            usr.setCi(rs.getString("ci"));
            usr.setNombres(rs.getString("nombres"));
            usr.setApellidos(rs.getString("apellidos"));
            usr.setDireccion(rs.getString("direccion"));
            usr.setCalular(rs.getString("celular"));
            usr.setEmail(rs.getString("email"));
        }
        ConexionDB.desconectar(conn);
        return usr;
    }
    
    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        String sql = "select * from usuario";
        Connection conn=ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList<>();
        while (rs.next()) {            
            Usuario usu = new Usuario();
            
            usu.setId_usuario(rs.getInt("id_usuario"));
            usu.setCi(rs.getString("ci"));
            usu.setNombres(rs.getString("nombres"));
            usu.setApellidos(rs.getString("apellidos"));
            usu.setDireccion(rs.getString("direccion"));
            usu.setCalular(rs.getString("celular"));
            usu.setEmail(rs.getString("email"));
            
            lista.add(usu);
        }
        ConexionDB.desconectar(conn);
        return lista;
    }
    
}
