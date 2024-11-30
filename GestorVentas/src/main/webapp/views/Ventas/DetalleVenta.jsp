<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles de Venta</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Detalles de la Venta</h1>
        <hr/>

        <div class="mb-3">
            <p><strong>ID Venta:</strong> ${venta.idVenta}</p>
            <p><strong>Usuario:</strong> ${venta.nombreUsuario}</p>
            <p><strong>Total:</strong> ${venta.total}</p>
            <p><strong>Fecha de Venta:</strong> ${venta.fechaVenta}</p>
        </div>

        <h2 class="mt-4">Artículos Comprados</h2>
        <c:if test="${empty venta.articulos}">
            <p class="text-danger">No hay artículos en esta venta.</p>
        </c:if>

        <c:if test="${not empty venta.articulos}">
            <table class="table table-striped table-bordered mt-3">
                <thead class="thead-dark">
                    <tr>
                        <th>Código de Artículo</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="articulo" items="${venta.articulos}">
                        <tr>
                            <td>${articulo.codigo}</td>
                            <td>${articulo.nombre}</td>
                            <td>${articulo.stock}</td> 
                            <td>${articulo.precio}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div class="text-center mt-4">
            <a href="VentasController?accion=historial" class="btn btn-primary">Volver al Historial</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>