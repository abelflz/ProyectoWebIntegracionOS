<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="UTF-8">
        <title>Sistema de Cheques</title>
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
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="<c:url value="ConceptosPagoIndex.com" />"/>Conceptos de Pago</a></li>
                        <li><a href="<c:url value="ProveedoresIndex.com"/>">Proveedores</a></li>
                        <li><a href="RegistroSolicitudChequesIndex.com">Registro de Solicitud de Cheques</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">
            <p>
                <a href="<c:url value="Integrando.com"/>" class="btn btn-success align-middle"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Enviar Servicio Web</a>
            </p>

            <table class="table table-striped table-responsive table-hover table-bordered">
                <tr>
                    <th>
                        Identificador
                    </th>
                    <th>
                        Descripción
                    </th>
                    <th>
                        Tipo Movimiento
                    </th>
                    <th>
                        Monto Total
                    </th>
                </tr>
                <c:forEach begin="0" end="2" step="1" items="${Cuentas}" var="cuenta" varStatus="loop">
                    <tr>
                        <td>
                            <c:out value="${cuenta.CuentaId}"/>
                        </td>
                        <td>
                            <c:out value="${cuenta.Descripcion}"/>
                        </td>
                        <td>
                            <c:out value="${cuenta.TipoMovimiento}"/>
                        </td>
                        <td>
                            <c:out value="${cuenta.monto}"/>
                        </td>
                    </c:forEach>
                </tr>
            </table>
            <form:form commandName="fecha" method="post">
                <p>
                    <form:label path="fechainicio">Fecha Inicio: </form:label>
                    <form:input type="date" path="fechainicio" cssClass="form-control"/>    
                </p>
                <p>
                    <form:label path="fechafin">Fecha Final: </form:label>
                    <form:input type="date" path="fechafin" cssClass="form-control col-xs-2"/>    
                </p>
                <p></p>
                <button class="btn btn-success">Filtrar</button>
            </form:form>
        </div>
    </body>
</html>