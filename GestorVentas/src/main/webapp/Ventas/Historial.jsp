<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
		<!-- TODAVIA NOSE SI FUNCIONA ES MAS QUE NADA LA MAQUETA -->
    <title>Historial de Ventas</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Historial de Ventas</h1>

    <c:if test="${empty ventas}">
        <p>No hay ventas registradas.</p>
    </c:if>

    <c:if test="${not empty ventas}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID Venta</th>
                    <th>Usuario</th>
                    <th>Total</th>
                    <th>Detalles</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="venta" items="${ventas}">
                    <tr>
                        <td>${venta.id}</td>
                        <td>${venta.nombreUsuario}</td>
                        <td>${venta.total}</td>
                        <td><a href="DetallesVenta?id=${venta.id}">Ver Detalles</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>