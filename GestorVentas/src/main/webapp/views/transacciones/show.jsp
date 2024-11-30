<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Transaccion</title>
</head>
<body>
    <h1>Detalles de la Transacción</h1>

    <p>Transaccion de: <c:out value="${transaccion.idUsuarioDeudor}"></c:out></p>
    <p>Hacia: <c:out value="${transaccion.idUsuarioBeneficiario}"></c:out></p>
    <p>Monto: <c:out value="${transaccion.montoTransaccionado}"></c:out></p>

    <a href="TransaccionesController?accion=registros&idUsuario=${transaccion.idDeudor}">Ver Registros</a>
</body>
</html>
