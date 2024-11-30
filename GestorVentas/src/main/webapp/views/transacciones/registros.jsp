<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Transacciones</title>
</head>
<body>
    <h1>Registros de Transacciones</h1>

     <h2>Transacciones recibidas</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Transacción</th>
                <th>Monto</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="transaccion" items="${beneficiario}">
                <tr>
                    <td><c:out value= "${transaccion.id}"></c:out></td>
                    <td><c:out value="${transaccion.monto}"></c:out></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Deudor -->
    <h2>Transacciones hechas</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Transacción</th>
                <th>Monto</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="transaccion" items="${deudor}">
                <tr>
                    <td><c:out value="${transaccion.id}"></c:out></td>
                    <td><c:out value="${transaccion.monto}"></c:out></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</body>
</html>
