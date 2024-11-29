<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
 <h1>Iniciar Sesión</h1>

    <%-- Mostrar mensaje de error si existe --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="auth" method="post">
        <p>
            Nombre de Usuario: <input type="text" name="nombreUsuario" value=""/>
        </p>
        <p>
            Contraseña: <input type="password" name="contrasenia" value=""/>
        </p>
        <input type="submit" value="Iniciar Sesión"/>
    </form>
    
    <p>
    ADMIN: usuario/clave ("admin")
    </p>
    <p>
    CLIENTE: usuario/clave ("cliente")
    </p>
</body>
</html>