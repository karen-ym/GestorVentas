<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Artículo</title>
</head>
<body>
	<h1>Editar Artículo</h1>

	<form action="articulos" method="post">
		<input type="hidden" name="accion" value="update">
		<input type="hidden" name="codigo" value="${articulo.codigo}"> 
		<p>
			Nombre: <input type="text" name="nombre" value="${articulo.nombre}" />
		</p>
		<p>
			Descripción: <input type="text" name="descripcion" value="${articulo.descripcion}"/>
		</p>
		<p>
			Precio: <input type="number" step="0.01" name="precio" value="${articulo.precio}"/>
		</p>
		<p>
			Stock: <input type="number" name="stock" value="${articulo.stock}"/>
		</p>
		<input type="submit" value="Guardar Cambios"/>
	</form>
</body>
</html>