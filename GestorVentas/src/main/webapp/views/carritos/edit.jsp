<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		
	<form action="Carritos" method="post">
		<input type="hidden" value="update" name="accion">
		<input type="hidden" value="${idUsuario}" name="idUsuario">
		<p>
			Precio: <c:out value="${articulo.codigo }" />
	        <input type="hidden" name="codigo" value="${articulo.codigo}">
		</p>
		<p>
			Nombre: <c:out value="${articulo.nombre }" />
		</p>
		<p>
			Precio: <c:out value="${articulo.precio }" />
		</p>
		<p>
			Cantidad: <input type="number" name="cantidad" value="${articulo.stock}"/>
		</p>
		<input type="submit" value="Editar" />
	</form>
</body>
</html>