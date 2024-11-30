<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Historial de Ventas</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Historial de Ventas</h1>
        <hr/>

        <c:if test="${empty listaVentas}">
            <p class="text-danger text-center">No hay ventas registradas.</p>
        </c:if>

        <c:if test="${not empty listaVentas}">
            <table class="table table-striped table-bordered table-hover mt-3">
                <thead class="thead-dark">
                    <tr>
                        <th>ID Venta</th>
                        <th>Usuario</th>
                        <th>Total</th>
                        <th>Detalles</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="venta" items="${listaVentas}">
                        <tr>
                            <td>${venta.idVenta}</td>
                            <td>${venta.nombreUsuario}</td>
                            <td>${venta.total}</td>
                            <td>
                                <a href="VentasController?accion=detalleVenta&id=${venta.idVenta}" class="btn btn-info btn-sm">
                                    Ver Detalles
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>