
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.impl.ClienteDAOimpl;
import com.emergentes.modelo.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ClienteDAO dao = new ClienteDAOimpl();
            Cliente cli = new Cliente();
            int id_cliente;
 
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
 
            System.out.println("VERIFICANDO");
            switch (action) {
                case "add":
                    request.setAttribute("cliente", cli);
                    request.getRequestDispatcher("frmcliente.jsp").forward(request, response);
                    break;
                case "edit":
                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    cli = dao.getById(id_cliente);
                    request.setAttribute("cliente", cli);
                    request.getRequestDispatcher("frmcliente.jsp").forward(request, response);
                    break;
                case "delete":
                    id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                    dao.delete(id_cliente);
                    response.sendRedirect("ClienteControlador");
                    break;
                case "view":
                    List<Cliente> lista = dao.getAll();
                    request.setAttribute("clientes", lista);
                    request.getRequestDispatcher("clientes.jsp").forward(request, response);
                    break;                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        String ci = request.getParameter("ci");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direcion");
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        
        Cliente cli = new Cliente();
        
        cli.setId_cliente(id_cliente);
        cli.setCi(ci);
        cli.setNombres(nombres);
        cli.setApellidos(apellidos);
        cli.setDireccion(direccion);
        cli.setEmail(email);
        cli.setCelular(celular);
        
        ClienteDAO dao = new ClienteDAOimpl();
        
            if (id_cliente == 0) {
            try {
                // Nuevo
                dao.insert(cli);
            } catch (Exception ex) {
                System.out.println("Error al Insertar: "  + ex.getMessage());
            }
        } else {
            try {
                // Edicion
                dao.update(cli);
            } catch (Exception ex) {
                System.out.println("Error al Actualizar: " + ex.getMessage());
            }
        }
        
        response.sendRedirect("ClienteControlador");
    }

}
