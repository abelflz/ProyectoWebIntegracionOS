<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Cheques</title>
        <link rel="stylesheet" href="<c:url value ="Resources/css/bootstrap.min.css"/>"/>
        <link rel="stylesheet" href="<c:url value ="Resources/css/LogIn/Theme.css"/>"/>
    </head>
    <body>
        <div>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Cheques</a>
                    </div>
                    <ul class="nav navbar-nav">
                    </ul>
                </div>
            </nav>
        </div>
        <div class="login-page">
            <div class="form">
                <form:form method="post" commandName="login">
                    <form:input type="text" path="Usuario" placeholder="Usuario" class="form-control" />
                    <form:input type="password" path="Clave" placeholder="Contraseña" class="form-control"/>
                    <button class="btn">LOGIN</button>
                    <p>
                        <%
                        if(request.getParameter("message") == null){

                        }else{
                        %>
                        <div class="alert alert-danger">Usuario o contraseña no válido.</div>
                        <%}%>
                    </p>
                </form:form>
            </div>
        </div>
    </body>
</html>
