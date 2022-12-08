<%
    if (session.getAttribute("logueado") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>

<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="content-wrapper" >
    <section class="content-header">
        <h1>Agregar Cliente</h1>
      
    </section>
</div>
<%@ include file="commons/footer.jspf"%>
