package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.impl.ClienteDAOimpl;
import com.emergentes.dao.impl.ProductoDaoImpl;
import com.emergentes.dao.impl.VentaDaoImpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.DetalleVenta;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Usuario;
import com.emergentes.modelo.Venta;
import com.emergentes.utiles.Comprobante;
import com.emergentes.utiles.Serie;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VentaControlador extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("VentaControlador");
    DecimalFormat df=new DecimalFormat("0.00");
    Cliente cliente = null;
    Producto producto = null;
    double total  = 0.00;
    List<DetalleVenta> detalleVentas = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            ClienteDAO cdao = new ClienteDAOimpl();
            ProductoDAO pdao = new ProductoDaoImpl();
            VentaDAO vdao = new VentaDaoImpl();
            HttpSession session = request.getSession();
            switch (action) {
                case "new":
                    cliente = null;
                    producto = null;
                    detalleVentas = new ArrayList<>();
                    total=0.00;
                    request.setAttribute("clienteEcontrado", cliente);
                    request.setAttribute("productoEcontrado", producto);
                    request.setAttribute("detalleVentas", detalleVentas);
                    request.setAttribute("numeroVenta", Serie.getSerie(vdao.getLastIdNum()));
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
                    break;
                case "list":
                    total=0.00;
                    request.setAttribute("clienteEcontrado", cliente);
                    request.setAttribute("productoEcontrado", producto);
                    request.setAttribute("detalleVentas", detalleVentas);
                    request.setAttribute("numeroVenta", Serie.getSerie(vdao.getLastIdNum()));
                    for (DetalleVenta detalleVenta : detalleVentas) {
                        total=total+detalleVenta.getPrecioTotal();
                    }
                    request.setAttribute("total",df.format(total));
                    request.getRequestDispatcher("venta.jsp").forward(request, response);
                    break;
                case "buscarCliente":
                    int ciCliente = Integer.parseInt(request.getParameter("txtCI"));
                    cliente = cdao.getById(ciCliente);
                    request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
                    break;
                case "buscarProducto":
                    int idProduct = Integer.parseInt(request!=null?request.getParameter("txtId"):"0");
                    producto = pdao.getById(idProduct);
                    request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
                    break;
                case "agregarProducto":
                    if (producto != null && Integer.parseInt(request.getParameter("txtId")) != 0) {
                        DetalleVenta detalleVenta = new DetalleVenta();
                        detalleVenta.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                        detalleVenta.setPrecioVenta(Double.parseDouble(request.getParameter("txtPrecio")));
                        detalleVenta.setProducto(producto);
                        boolean existeProducto = false;
                        for (int i = 0; i < detalleVentas.size(); i++) {
                            if (producto.getId() == detalleVentas.get(i).getProducto().getId()) {
                                detalleVentas.get(i).setCantidad(detalleVentas.get(i).getCantidad() + detalleVenta.getCantidad());
                                existeProducto = true;
                            }
                        }
                        if (!existeProducto) {
                            detalleVentas.add(detalleVenta);
                        }
                    }
                    request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
                    break;
                case "generarVenta":
                    Venta venta = new Venta();
                    venta.setCliente(cliente);
                    venta.setUsuario((Usuario) session.getAttribute("usuario"));
                    venta.setFechaVenta(new Date(new java.util.Date().getTime()));
                    venta.setDetalleVenta(detalleVentas);
                    if (detalleVentas.size() >= 1) {
                        int idVenta = vdao.insert(venta);
                        venta.setId(idVenta);
                        Comprobante.generarPDF(venta);
                        String filepath = "C:app-web-proyecto\\src\\main\\webapp\\comprobantes\\".concat(venta.getNumeroVenta() + ".pdf");
                        print(filepath);

                        request.getRequestDispatcher("VentaControlador?action=new").forward(request, response);
                    } else {
                        request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
                    }

                    break;
                case "delete":
                    int idProd = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < detalleVentas.size(); i++) {
                        if (idProd == detalleVentas.get(i).getProducto().getId()) {
                            detalleVentas.remove(i);
                        }
                    }
                    request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
                    break;
                case "view":
                    request.getRequestDispatcher("adminventa.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            request.getRequestDispatcher("VentaControlador?action=list").forward(request, response);
        }
    }

    public void print(String filename) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(filename);
        try {
            desktop.open(file);
            //desktop.print(file);
        } catch (IOException e) {
            System.err.println("Error:" + e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
