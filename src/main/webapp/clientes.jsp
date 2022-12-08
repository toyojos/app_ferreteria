<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>

<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="content-wrapper" >
    <section class="content-header">
        <h1>Clientes</h1>
        <br>
        <a href="ClienteControlador?action=add" class="btn btn-primary btn-sm"><i class="fa-solid fa-circle-plus"></i>Nuevo</a>
        <br><br>
        <table class="table table-striped">
            <tr>
                <th>ID_CLIENTE</th>
                <th>CI</th>
                <th>NOMBRES</th>
                <th>APELLIDOS</th>
                <th>DIRECCION</th>
                <th>EMAIL</th>
                <th>CELULAR</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${clientes}">
                <tr>
                    <td>${item.id_cliente}</td>
                    <td>${item.ci}</td>
                    <td>${item.nombres}</td>
                    <td>${item.apellidos}</td>
                    <td>${item.direccion}</td>
                    <td>${item.email}</td>
                    <td>${item.celular}</td>
                    <td><a href="ClienteControlador?action=edit&id=${item.id_cliente}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                    <td><a href="ClienteControlador?action=delete&id=${item.id_cliente}"><i class="fa-solid fa-trash"></i></a></td>
                </tr>
            </c:forEach>
        </table>
    </section>
</div>
<%@ include file="commons/footer.jspf"%>
