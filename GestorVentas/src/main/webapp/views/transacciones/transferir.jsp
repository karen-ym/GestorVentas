<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Transacciones</title>
</head>
<body>
    <h1>Transferir Dinero</h1>

    <form action="TransaccionesController" method="post">
        <input type="hidden" name="idDeudor" value="${usuario.id}">
        
        <label for="idBeneficiario">ID:</label>
        <input type="text" id="idBeneficiario" name="idBeneficiario"><br><br>

        <label for="monto">Monto a Transferir:</label>
        <input type="number" id="monto" name="monto"><br><br>

        <button type="submit">Realizar Transferencia</button>
    </form>

    <a href="usuarios?accion=show&id=${usuario.id}">Volver</a>
</body>
</html>
