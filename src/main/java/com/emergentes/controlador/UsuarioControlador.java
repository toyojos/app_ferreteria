
package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.impl.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAOimpl();
            Usuario usr = new Usuario();
            int id_usuario;
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch (action) {
                case "add":
                    request.setAttribute("usuario", usr);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    usr = dao.getById(id_usuario);
                    request.setAttribute("usuario", usr);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                    dao.delete(id_usuario);
                    response.sendRedirect("UsuarioControlador");
                    break;
                case "view":
                    List<Usuario> lista = dao.getAll();
                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        String ci = request.getParameter("ci");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        
        Usuario usr = new Usuario();
        
        usr.setId_usuario(id_usuario);
        usr.setCi(ci);
        usr.setNombres(nombres);
        usr.setApellidos(apellidos);
        usr.setDireccion(direccion);
        usr.setCalular(celular);
        usr.setEmail(email);
        usr.setContrasena(contrasena);

        UsuarioDAO dao = new UsuarioDAOimpl();
        
        if (id_usuario == 0) {
            try {
                // Nuevo
                dao.insert(usr);
            } catch (Exception ex) {
                System.out.println("Error al Insertar: "  + ex.getMessage());
            }
        } else {
            try {
                // Edicion
                dao.update(usr);
            } catch (Exception ex) {
                System.out.println("Error al Actualizar: " + ex.getMessage());
            }
        }
        response.sendRedirect("UsuarioControlador");
    
    }


}
