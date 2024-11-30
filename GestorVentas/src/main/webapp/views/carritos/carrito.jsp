<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito</title>
<link rel="stylesheet" href="css/estiloCliente.css">
</head>
<body>
	<div class="container">
        <h1>Carrito</h1>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Código</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="articulo" items="${articulos}">
                    <tr>
                        <td><c:out value="${articulo.nombre}" /></td>
                        <td><c:out value="${articulo.descripcion}" /></td>
                        <td><c:out value="${articulo.precio}" /></td>
                        <td><c:out value="${articulo.stock}" /></td>
                        <td><c:out value="${articulo.codigo}" /></td>
                        <td>
                            <a href="Carritos?accion=show&codigo=${articulo.codigo}&idUsuario=${idUsuario}" class="btn-link">Detalle</a>
                            <a href="Carritos?accion=edit&codigo=${articulo.codigo}&idUsuario=${idUsuario}" class="btn-link">Editar</a>
                            <form action="Carritos" method="post" style="display:inline;">
                                <input type="hidden" name="codigo" value="${articulo.codigo}">
                                <input type="hidden" name="idUsuario" value="${idUsuario}">
                                <input type="hidden" name="accion" value="delete">
    							<button type="submit" class="btn btn-link">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <strong>Precio Total: </strong><c:out value="${precioTotal}" />
        </div>
        
        <form action="Carritos" method="post" class="mt-5">
            <input type="hidden" name="idUsuario" value="${idUsuario}">
            <input type="hidden" name="accion" value="comprar">
            <button type="submit" class="btn btn-link">Comprar Carrito</button>
        </form>
    </div>
</body>
</html>