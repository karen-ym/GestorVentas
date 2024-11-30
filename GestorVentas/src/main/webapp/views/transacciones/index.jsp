<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Transacciones</title>
</head>
<body>
    <h1>Detalles del usuario</h1>

    <p>ID: <c:out value="${usuario.id}"></c:out></p>
    <p>Nombre: <c:out value="${usuario.nombreUsuario}"></c:out></p>

    <a href="TransaccionesController?accion=transferir&idDeudor=${usuario.id}">Transferir Dinero</a>
    <a href="TransaccionesController?accion=registros&idUsuario=${usuario.id}">Ver Registros de Transacciones</a>
</body>
</html>
