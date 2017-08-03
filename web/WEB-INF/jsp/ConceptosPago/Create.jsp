<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creando Concepto de Pago</title>
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
                        <li><a href="<c:url value="index.com"/>">Home</a></li>
                        <li class="active"><a href="<c:url value="ConceptosPagoIndex.com"/>">Conceptos de Pago</a></li>
                        <li><a href="<c:url value="ProveedoresIndex.com"/>">Proveedores</a></li>
                        <li><a href="<c:url value="RegistroSolicitudChequesIndex.com"/>">Registro de Solicitud de Cheques</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="<c:url value="ConceptosPagoIndex.com" />">Listado de productos</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">Formulario de Conceptos de Pago</div>
                <div class="panel-body">
                    <form:form method="post" commandName="conceptopagos">
                        <h3>Complete el Formulario</h3>
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                        <p>
                            <form:label path="descripcion">Descripción:</form:label>
                            <form:errors path="descripcion" element="div" cssClass="alert alert-danger"/>
                            <form:input path = "descripcion" cssClass="form-control"/>
                        </p>    
                        <p>
                            <form:label path="estado">Estado:</form:label>
                            <form:select path ="estado" cssClass="form-control">
                                <option value = "-1"></option>
                                <form:options items="${ListaEstados}"/>
                            </form:select>
                        </p>
                        <input type="submit" value="Enviar" class="btn btn-danger" />
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>