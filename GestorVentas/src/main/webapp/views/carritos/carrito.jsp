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
	<h1>Carrito</h1>
		<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th>Precio</th>
				<th>Stock</th>
				<th>codigo</th>
				<th></th>
				<th></th>
			</tr> 
		</thead>
		
		<tbody>
	    	<c:forEach var="articulo" items="${articulos}">
				<tr>
					<td><c:out value="${articulo.nombre }" /></td>
					<td><c:out value="${articulo.descripcion }" /></td>
					<td><c:out value="${articulo.precio }" /></td>
					<td><c:out value="${articulo.stock }" /></td>
					<td><c:out value="${articulo.codigo }" /></td>
					<td>
						<form action="Carritos" method="post">
							<input type="hidden" name="codigo" value="${articulo.codigo}">
							<input type="hidden" name="accion" value="delete">
							<input type="submit" value="Elimiar Articulo">
						</form>
					</td>
					<td><a href="Carritos?accion=edit&codigo=${articulo.codigo}&idUsuario=${idUsuario}">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		Precio Total: <c:out value="${precioTotal}" />
</body>
</html>