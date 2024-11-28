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
		<h1>Tienda</h1>
		<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th>Precio</th>
				<th>Stock</th>
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
					<td>
						<a href="Carritos?accion=add&idUsuario=${idUsuario}&codigo=${articulo.codigo}">Comprar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<a href="Carritos?accion=carrito&idUsuario=${idUsuario}">Ver Carrito</a>
</body>
</html>