<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articulo</title>
</head>
<body>
	<form action="Carritos" method="post">
	    <input type="hidden" name="accion" value="insert">
	    <input type="hidden" name="idUsuario" value="${idUsuario}">
	    <p>
	        Código: <span>${articulo.codigo}</span>
	        <input type="hidden" name="codigo" value="${articulo.codigo}">
	    </p>
	    <p>
	        Nombre: <span>${articulo.nombre}</span>
	        <input type="hidden" name="nombre" value="${articulo.nombre}">
	    </p>
	    <p>
	        Descripción: <span>${articulo.descripcion}</span>
	        <input type="hidden" name="descripcion" value="${articulo.descripcion}">
	    </p>
	    <p>
	        Precio: <span>${articulo.precio}</span>
	        <input type="hidden" name="precio" value="${articulo.precio}">
	    </p>
	    
	    <p>
	    	Cantidad:
	    	<input type="number" name="cantidad" value="${1}">
	    </p>
	
	    <input type="submit" value="Agregar al carrito">
	</form>

	
</body>
</html>