<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Artículos</title>
</head>
<body>

	<a href="articulos?accion=create">Agregar Artículo</a>

	<table border="1">
		<thead>
			<tr>
				<th>Código</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Precio</th>
				<th>Stock</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="articulo" items="${listaArticulos}"> 
				<tr>
					<td><c:out value="${articulo.codigo}" /></td>
					<td><c:out value="${articulo.nombre}" /></td>
					<td><c:out value="${articulo.descripcion}" /></td>
					<td><c:out value="${articulo.precio}" /></td>
					<td><c:out value="${articulo.stock}" /></td>
					<td><a href="articulos?accion=show&codigo=${articulo.codigo}">ver</a></td>
					<td><a href="articulos?accion=edit&codigo=${articulo.codigo}">editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>