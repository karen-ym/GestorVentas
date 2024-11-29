<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear</title>
</head>
<body>
		<h1>Alta</h1>
			
			<form action="usuarios" method="post">
			<input type="hidden" value="insert" name="accion" />
				<p>
					Usuario: <input value="" name="nombreUsuario" />
				</p>
				<p>
					Contraseña: <input value="" name="contrasenia" />
				</p>
				<p>
					Saldo: <input value="" name="saldoActual" />
				</p>
				<p>
					Tipo:<select name="tipo">
					<option value="">----</option>
                    <option value="CLIENTE">Cliente</option>
                    <option value="EMPLEADO">Empleado</option>
                    </select>
				</p>
				<input type="submit" value="Register" />
			</form>
</body>
</html>