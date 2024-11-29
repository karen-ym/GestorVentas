
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar Usuarios</title>
</head>
<body>
		<h1>Usuario</h1>
			<p>Nombre: <c:out value="${usuario.nombreUsuario}"></c:out> </p>
			<p>Contraseña: <c:out value="${usuario.contrasenia}"></c:out> </p>
			<p>Saldo: <c:out value="${usuario.saldoActual}"></c:out> </p>
			<p>Tipo: <c:out value="${usuario.tipo}"></c:out></p>
			
			<form action="usuarios" method="post">
				<input type="hidden" name="id" value="${usuario.id}">
				<input type="hidden" name="accion" value="delete">
				<input type="submit" value="Eliminar usuario"> 
</body>
</html>