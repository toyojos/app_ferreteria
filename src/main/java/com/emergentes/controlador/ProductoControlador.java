
package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.impl.ProductoDaoImpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            ProductoDAO pao = new ProductoDaoImpl();
            Producto prod = new Producto();
            int id_producto;
 
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
 
            System.out.println("VERIFICANDO");
            switch (action) {
                case "add":
                    request.setAttribute("cliente", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    prod = pao.getById(id_producto);
                    request.setAttribute("producto", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "delete":
                    id_producto = Integer.parseInt(request.getParameter("id_producto"));
                    pao.delete(id_producto);
                    response.sendRedirect("ProductoControlador");
                    break;
                case "view":
                    List<Producto> lista = pao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    break;                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
