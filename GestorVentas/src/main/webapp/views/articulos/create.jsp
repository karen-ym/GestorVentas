<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Artículo</title>
</head>
<body>
	<h1>Crear Artículo</h1>

	<form action="articulos" method="post">
		<input type="hidden" name="accion" value="insert">
		<p>
			Nombre: <input type="text" name="nombre" value=""/>
		</p>
		<p>
			Descripción: <input type="text" name="descripcion" value=""/>
		</p>
		<p>
			Precio: <input type="number" step="0.01" name="precio" value=""/>
		</p>
		<p>
			Stock: <input type="number" name="stock" value=""/>
		</p>
		<input type="submit" value="Crear"/>
	</form>
</body>
</html>