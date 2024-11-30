<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Editar Art�culo</title>
    <link rel="stylesheet" type="text/css" href="estiloCliente.css">
</head>
<body>

	<div class="container">
        <h1>Editar Art�culo en el Carrito</h1>
        <form action="Carritos" method="post">
            <input type="hidden" value="update" name="accion">
            <input type="hidden" value="${idUsuario}" name="idUsuario">
            
            <div class="form-group">
                <label>C�digo:</label>
                <span><c:out value="${articulo.codigo}" /></span>
                <input type="hidden" name="codigo" value="${articulo.codigo}">
            </div>

            <div class="form-group">
                <label>Nombre:</label>
                <span><c:out value="${articulo.nombre}" /></span>
            </div>

            <div class="form-group">
                <label>Precio:</label>
                <span><c:out value="${articulo.precio}" /></span>
            </div>

            <div class="form-group">
                <label for="cantidad">Cantidad:</label>
                <input type="number" id="cantidad" name="cantidad" value="${articulo.stock}" class="input-field">
            </div>

            <button type="submit" class="button">Editar</button>
        </form>
    </div>
</body>
</html>