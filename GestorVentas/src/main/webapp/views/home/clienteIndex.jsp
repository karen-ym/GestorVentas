<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido Cliente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h1 class="mb-4">Bienvenido, <c:out value="${usuario.nombreUsuario}" /></h1>
    
    <div class="card mb-4">
        <div class="card-body">
            <p><strong>Nombre de usuario:</strong> <c:out value="${usuario.nombreUsuario}" /></p>
            <p><strong>Saldo:</strong> $<c:out value="${usuario.saldoActual}" /></p>
            <p><strong>Tipo de usuario:</strong> <c:out value="${usuario.tipo}" /></p>
            <form action="usuarios" method="post" class="mt-2">
                <input type="hidden" name="id" value="${usuario.id}">
                <input type="hidden" name="accion" value="delete">
                <button type="submit" class="btn btn-danger">Eliminar cuenta</button>
            </form>
        </div>
    </div>

    <!-- Sección de artículos -->
    <h2 class="mb-3">Artículos Disponibles</h2>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="articulo" items="${listaArticulos}">
                <tr>
                    <td><c:out value="${articulo.codigo}" /></td>
                    <td><c:out value="${articulo.nombre}" /></td>
                    <td><c:out value="${articulo.descripcion}" /></td>
                    <td>$<c:out value="${articulo.precio}" /></td>
                    <td><c:out value="${articulo.stock}"/></td>
                    <td>
                        <form action="carrito" method="post">
                            <input type="hidden" name="codigo" value="${articulo.codigo}">
                            <button type="submit" class="btn btn-primary btn-sm">Agregar al carrito</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="text-right mt-4">
        <a href="carrito" class="btn btn-success">Ver Carrito</a>
    </div>
</body>
</html>