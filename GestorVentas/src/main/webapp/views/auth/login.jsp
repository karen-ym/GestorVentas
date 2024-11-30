<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>

    <div>
        <div>

            <h1>Iniciar Sesión</h1>

            <c:if test="${not empty error}">
                <div>
                    ${error}
                </div>
            </c:if>

            <form action="auth" method="post">

                <div>
                    <label for="nombreUsuario">Nombre de Usuario</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                </div>

                <div class="form-group">
                    <label for="contrasenia">Contraseña</label>
                    <input type="password" class="form-control" id="contrasenia" name="contrasenia" required>
                </div>

                <button type="submit">Iniciar Sesión</button>
            </form>

			<a href="${pageContext.request.contextPath}/usuarios?accion=create">Registrarse</a> <p> <p>
			<div>
            	<p>Usuarios de prueba disponibles:</p>
                <p>ADMIN -> usuario/clave: admin</p>
                <p>CLIENTE -> usuario/clave: cliente</p>
            </div>
        </div>
    </div>

</body>
</html>