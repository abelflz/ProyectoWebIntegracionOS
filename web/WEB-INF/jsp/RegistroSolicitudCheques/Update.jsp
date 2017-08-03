<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Solicitud de Cheque</title>
        <link rel="stylesheet" href="<c:url value ="Resources/css/bootstrap.min.css"/>"/>
        <script type="text/javascript" src="<c:url value ="Resources/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value ="Resources/js/jquery-ui-1.9.2.custom.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value ="Resources/js/npm.js"/>"></script>
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
                        <li><a href="<c:url value="ProveedoresIndex.com"/>">Proveedores</a></li>
                        <li class = "active"><a href="<c:url value="RegistroSolicitudChequesIndex.com"/>">Registro de Solicitud de Cheques</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="<c:url value="RegistroSolicitudChequesIndex.com" />">Listado de Solicitudes</a></li>
                <li class="active">Editar</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">Formulario de Registro de Solicitud de Cheques</div>
                <div class="panel-body">
                    <form:form method="post" commandName="registro">
                        <h3>Complete el Formulario</h3>
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                        <p>
                            <form:label path="ProveedorId">Proveedor</form:label>
                            <form:select path="ProveedorId" cssClass="form-control">
                            <form:options items="${ListaProveedores}"/>
                        </form:select>
                        </p>
                        <p>
                            <form:label path="Monto">Monto</form:label>
                            <form:input path = "Monto" cssClass="form-control"/>
                        </p>
                        <p>
                            <form:label path="FechaRegistro">Fecha de Registro</form:label>
                            <form:input path="FechaRegistro" type='text' class="form-control"/> 
                        </p>
                        <p>
                            <form:label path="estado">Estado:</form:label>
                            <form:select path ="estado" cssClass="form-control">
                                <form:option value = ""></form:option>
                                <form:options items="${ListaEstados}"/>
                            </form:select>
                        </p>
                        <p>
                            <form:label path="CuentaContableProveedor">Cuenta Contable Proveedor</form:label>
                            <form:input path = "CuentaContableProveedor" cssClass="form-control" readonly="true"/>
                        </p>
                        <p>
                            <form:label path="CuentaContableRelCCBanco">Cuenta Contable Relacionado Cuenta Corriente del Banco</form:label>
                            <form:input path = "CuentaContableRelCCBanco" cssClass="form-control"/>
                        </p>
                        <p>
                            <form:label path="ConceptoId">Concepto de Pago</form:label>
                            <form:select path="ConceptoId" cssClass="form-control">
                                <form:option value=""></form:option>
                                <form:options items="${ListaConceptosPagos}"/>
                            </form:select>
                        </p>
                        <input type="submit" value="Enviar" class="btn btn-danger" />
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>