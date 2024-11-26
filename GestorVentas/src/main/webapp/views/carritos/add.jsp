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
		<input type="hidden" name="accion" value="insert">
		<p>
			Código: <c:out value="${articulo.codigo}" />
		</p>
		<p>
			Nombre: <c:out value="${articulo.nombre}" />
		</p>
		<p>
			Descripción: <c:out value="${articulo.descripcion}" />
		</p>
		<p>
			Precio: <c:out value="${articulo.precio}" />
		</p>
		<p>
			<input type="hidden" name="idUsuario" value="${idUsuario}"/>
			Stock: <input type="number" name="stock" value=""/>
		</p>
		<input type="submit" value="Agregar al carrito"/>
	</form>
</body>
</html>