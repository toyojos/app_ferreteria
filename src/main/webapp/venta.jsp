<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>

<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="content-wrapper" >
    <section class="content-header">
        <h1>
            Nueva Venta
            <small>Generar una venta</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
            <li class="active">Panel Administrativo</li>
        </ol>
    </section>

    <section class="content">
        <div class="row">
            <div class="col-lg-4 col-xs-12">
                <form action="VentaControlador" method="POST">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <h3 class="box-title">Datos del Cliente</h3>
                        </div>                        
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <div class="input-group">
                                    <input type="text" name="txtCI" required="" value="${clienteEcontrado.id_cliente}" placeholder="Ingrese Codigo Cliente" class="form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" name="action" value="buscarCliente" class="btn btn-info btn-flat">Buscar</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <input type="text" name="txtNombres" required="" value="${clienteEcontrado.nombres} ${clienteEcontrado.apellidos}" readonly="" placeholder="Juan Ramirez Torres" class="form-control">
                            </div>
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title">Datos del Producto</h3>
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <div class="input-group">
                                    <input type="text" name="txtId" value="${productoEcontrado.id}" placeholder="Ingrese Codigo Producto" class="form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" name="action" value="buscarProducto" class="btn btn-info btn-flat">Buscar</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <input type="text" name="txtNombreProducto" value="${productoEcontrado.nombre}" readonly="" placeholder="Teclado Lenovo 456" class="form-control">
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <input type="text" name="txtPrecio"  value="${productoEcontrado.precio}" placeholder="0.00" class="form-control">                                  
                            </div>                            
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <input type="number" name="txtCantidad"  value="1" placeholder="1" class="form-control">                                  
                            </div>                            
                        </div>
                        <div class="box-body">
                            <div class="col-lg-12 col-xs-12">
                                <input type="text" name="txtStock" value="${productoEcontrado.stock}" readonly="" placeholder="0.00" class="form-control">                                  
                            </div>                            
                        </div>
                        <div class="box-footer">
                            <div class="pull-right" style="margin-right: 15px">
                                <button type="submit" name="action" value="agregarProducto"    class="btn btn-success btn-flat">Agregar Producto</button>
                            </div>                        
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-8 col-xs-12">
                <div class="box box-warning">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-lg-8 col-xs-12" style="padding-left: 30px;padding-top: 10px">
                                <h3 class="box-title">Detalle Venta</h3>
                            </div>                            
                            <div class="col-lg-4 col-xs-12">
                                <div class="pull-right input-group">   
                                    <span class="input-group-btn">
                                        <a class="btn btn-default btn-flat">VENTA N°</a>
                                    </span>
                                    <input type="text" readonly="" value="${numeroVenta}"  class="form-control"/>          
                                </div> 
                            </div>                       
                        </div>                        
                    </div>
                    <div class="box-body">
                        <table class="table table-hover" style="width: 100%" id="example">
                            <thead>
                                <tr>
                                    <th  class="text-center">#</th>
                                    <th  class="text-center">DESCRIPCION</th>
                                    <th  class="text-center">PRECIO UNI.</th>                                    
                                    <th  class="text-center">CANTIDAD</th>                                    
                                    <th  class="text-center">TOTAL</th>                                    
                                    <th  class="text-center">ACCION</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="dv" items="${detalleVentas}" varStatus="pr">
                                    <tr>
                                        <td class="text-center">${pr.index+1}</td>                                      
                                        <td>${dv.producto.nombre}</td>
                                        <td>${dv.precioVentaf}</td>
                                        <td  class="text-center">${dv.cantidad}</td>
                                        <td>${dv.precioTotalf}</td>
                                        <td class="text-center d-flex">
                                            <form action="VentaControlador" method="POST">
                                                <input type="hidden" value="${dv.producto.id}" name="id">
                                                <button class="btn btn-outline-danger btn-flat btn-sm" type="submit" name="action" value="delete"><i class="fa fa-trash"></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>    
                    </div>
                    <div class="box-footer">
                        <form action="VentaControlador" method="POST">
                            <div class="pull-right">
                                <button type="submit" name="action" value="new" class="btn btn-danger btn-flat">Cancelar</button>
                                <button type="submit" name="action" value="generarVenta" class="btn btn-success btn-flat">Generar Venta</button>
                                <label>TOTAL A PAGAR : </label>
                                <a href="" class="btn btn-default btn-flat">${total}</a>
                            </div>  
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@ include file="commons/footer.jspf"%>
