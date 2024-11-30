<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>BIENVENIDO ADMIN</h1>
<table border="1">
<thead>
	<tr>
		<th>ID</th>
		<th>NombreUsuario</th>
		<th>Contraseña</th>
		<th>Saldo</th>
		<th>Tipo</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="usuario" items="${listarda}">
		<tr>
			<td> <c:out value="${usuario.id}"></c:out> </td>
			<td> <c:out value="${usuario.nombreUsuario}"></c:out> </td>
			<td> <c:out value="${usuario.contrasenia}"></c:out> </td>
			<td> <c:out value="${usuario.saldoActual}"></c:out> </td>
			<td> <c:out value="${usuario.tipo}"></c:out> </td>
			<td> <a href="usuarios?accion=show&id=${usuario.id}">Ver</a> </td>
			<td>
			<form action="usuarios" method="post">
				<input type="hidden" name="id" value="${usuario.id}">
				<input type="hidden" name="accion" value="delete">
				<input type="submit" value="Eliminar usuario">
			</form>
			</td>
			
				
	</c:forEach>

</tbody>

</table>

</body>
</html>
