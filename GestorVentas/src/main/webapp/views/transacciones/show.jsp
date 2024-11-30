<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Transacci�n</title>
    <link rel="stylesheet" type="text/css" href="css/estiloCliente.css">
</head>
<body>
    <div class="container">
        <h1>Detalles de la Transacci�n</h1>

        <p><strong>Transacci�n de:</strong> <c:out value="${transaccion.idUsuarioDeudor}"></c:out></p>
        <p><strong>Hacia:</strong> <c:out value="${transaccion.idUsuarioBeneficiario}"></c:out></p>
        <p><strong>Monto:</strong> <c:out value="${transaccion.montoTransaccionado}"></c:out></p>

        <div class="mt-3">
            <a href="TransaccionesController?accion=registros&idUsuario=${transaccion.idDeudor}" class="ver-carrito">Ver Registros</a>
        </div>
    </div>
</body>
</html>
