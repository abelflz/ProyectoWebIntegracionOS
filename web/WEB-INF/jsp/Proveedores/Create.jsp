<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creando Proveedor</title>
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
            <ol class="breadcrumb">
                <li><a href="<c:url value="ProveedoresIndex.com" />">Listado de Proveedores</a></li>
                <li class="active">Crear</li>
            </ol>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">Formulario de Proveedores</div>
                <div class="panel-body">
                    <form:form method="post" commandName="proveedores">
                        <h3>Complete el Formulario</h3>
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                        <p>
                            <form:label path="Nombre">Nombre y Apellido</form:label>
                            <form:input path = "Nombre" cssClass="form-control"/>
                        </p>   
                        <p>
                            <form:label path="TipoPersona">Tipo de Persona</form:label>
                            <form:select path="TipoPersona" cssClass="form-control">
                                <form:option value=""></form:option>
                                <form:options items="${ListaTipoPersonas}"/>
                            </form:select>
                        </p>
                        <p>
                            <form:label path="cedulaRNC">Cédula / RNC</form:label>
                            <form:input path = "cedulaRNC" cssClass="form-control"/>
                        </p>
                        <p>
                            <form:label path="Balance">Balance</form:label>
                            <form:input path = "Balance" cssClass="form-control"/>
                        </p>
                        <p>
                            <form:label path="CuentaContable">Cuenta Contable</form:label>
                            <form:input path = "CuentaContable" cssClass="form-control"/>
                        </p>
                        <p>
                            <form:label path="Estado">Estado:</form:label>
                            <form:select path ="Estado" cssClass="form-control">
                                <form:option value = ""></form:option>
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