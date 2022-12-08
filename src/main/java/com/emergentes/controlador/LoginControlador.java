/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        action = (action == null) ? "view" : request.getParameter("action");
        if (action.equals("logout")){
            HttpSession ses =  request.getSession();
            ses.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            System.out.println("HOLAAAAA");
            String email = request.getParameter("email");
            String contrasena = request.getParameter("contrasena");
            String sql = "select * from usuario where email = ? and contrasena=?";
            ResultSet rs;
            
            ConexionDB canal = new ConexionDB();
            Connection cn = canal.conectar();
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, contrasena);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                // Crear las variables de session para el uso en la aplicación
                HttpSession ses = request.getSession();
                
                ses.setAttribute("logueado", "OK");
                
                Usuario usuario=new Usuario();
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                ses.setAttribute("usuario", usuario);
                System.out.println("ENTRANDO CORRECTAMENTE");
                response.sendRedirect("Controlador?action=principal");
            }
            else{
                System.out.println("DATOS INCORRECTOS");
                response.sendRedirect("login.jsp");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos "+ ex.getMessage());
        }
    }

}
