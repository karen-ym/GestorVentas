<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles del Artículo</title>
</head>
<body>
	<h1>Artículo</h1>
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
		Stock: <c:out value="${articulo.stock}" />
	</p>

</body>
</html>