<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Proveedores</title>
        <link rel="stylesheet" href="<c:url value ="Resources/css/bootstrap.min.css"/>"/>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Cheques</a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li><a href="<c:url value="index.com"/>"/>Home</a></li>
                        <li><a href="<c:url value="ConceptosPagoIndex.com"/>">Conceptos de Pago</a></li>
                        <li class="active"><a href="<c:url value="ProveedoresIndex.com"/>">Proveedores</a></li>
                        <li><a href="<c:url value="RegistroSolicitudChequesIndex.com"/>">Registro de Solicitud de Cheques</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">
            <p>
                <a href="<c:url value="ProveedoresCreate.com" />" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Agregar</a>
            </p>
            <%
                if(request.getParameter("message") == null){
                
                }else{
            %>
            <div class="alert alert-danger">Existen registros de Solicitudes de cheques relacionados a este Proveedor.</div>
            <%}%>
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th>Identificador</th>
                    <th>Nombre</th>
                    <th>Tipo de Persona</th>
                    <th>Cédula</th>
                    <th>Balance</th>
                    <th>Cuenta Contable</th>
                    <th>Estado</th>
                    <th>Editar/Eliminar</th>
                </tr>
                <c:forEach items="${datos}" var = "dato">
                    <tr>
                        <td>
                            <c:out value="${dato.id}"/>
                        </td>
                        <td>
                            <c:out value="${dato.nombre}"/>
                        </td>
                        <td>
                            <c:out value="${dato.tipopersona}"/>
                        </td>
                        <td>
                            <c:out value="${dato.cedularnc}"/>
                        </td>
                        <td>
                            <c:out value="${dato.Balance}"/>
                        </td>
                        <td>
                            <c:out value="${dato.CuentaContable}"/>
                        </td>
                        <td>
                            <c:out value="${dato.Estado}"/>
                        </td>
                        <td>
                            <a href="<c:url value="ProveedoresEdit.com?id=${dato.id}" />"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                            <a href="<c:url value="ProveedoresDelete.com?id=${dato.id}" />"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
