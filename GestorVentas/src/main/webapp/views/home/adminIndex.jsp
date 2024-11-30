<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">BIENVENIDO ADMIN</h1>

    <!-- Sección de Usuarios -->
    <h2 class="mb-3">Gestión de Usuarios</h2>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nombre de Usuario</th>
                <th>Contraseña</th>
                <th>Saldo</th>
                <th>Tipo</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${listarda}">
                <tr>
                    <td><c:out value="${usuario.id}"/></td>
                    <td><c:out value="${usuario.nombreUsuario}"/></td>
                    <td><c:out value="${usuario.contrasenia}"/></td>
                    <td><c:out value="${usuario.saldoActual}"/></td>
                    <td><c:out value="${usuario.tipo}"/></td>
                    <td>
                        <a href="usuarios?accion=show&id=${usuario.id}" class="btn btn-info btn-sm">Ver</a>
                        <form action="usuarios" method="post" class="d-inline">
                            <input type="hidden" name="id" value="${usuario.id}">
                            <input type="hidden" name="accion" value="delete">
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Sección de Ventas -->
    <h2 class="mt-5 mb-3">Historial de Ventas</h2>
    <c:if test="${empty listaVentas}">
        <p class="text">No hay ventas registradas.</p>
    </c:if>
    <c:if test="${not empty listaVentas}">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID Venta</th>
                    <th>Usuario</th>
                    <th>Total</th>
                    <th>Fecha</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="venta" items="${listaVentas}">
                    <tr>
                        <td>${venta.idVenta}</td>
                        <td>${venta.nombreUsuario}</td>
                        <td>${venta.total}</td>
                        <td>${venta.fechaVenta}</td>
                        <td>
                            <a href="ventas?accion=detalleVenta&id=${venta.idVenta}" class="btn btn-info btn-sm">Detalles</a>
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