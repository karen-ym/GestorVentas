<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>BIENVENIDO CLIENTE</h1>
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
