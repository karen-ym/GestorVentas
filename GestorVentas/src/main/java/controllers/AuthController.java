package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Usuario;
import repositories.UsuariosRepoSingleton;
import repositories.interfaces.UsuariosRepo;

@WebServlet("/auth")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuariosRepo usuariosRepo;

    public AuthController() {
        this.usuariosRepo = UsuariosRepoSingleton.getInstance(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");

        // Manejo de errores
        if (nombreUsuario == null || nombreUsuario.isEmpty() || contrasenia == null || contrasenia.isEmpty()) {
            request.setAttribute("error", "Nombre de usuario y contraseña son obligatorios.");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
            return;
        }
        
        Usuario usuario = usuariosRepo.findByNombreUsuario(nombreUsuario);

        // Iniciar sesión:
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) { 
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // Redirigir al usuario según rol (REVISAR DESPUÉS)
            if (usuario.getTipo().equals("empleado")) {
                request.getRequestDispatcher("/views/home/adminIndex.jsp").forward(request, response); 
            } else if (usuario.getTipo().equals("cliente")) {
                request.getRequestDispatcher("/views/home/clienteIndex.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/views/home/clienteIndex.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }
    }
    
    public static Usuario obtenerUsuarioActual(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Usuario) session.getAttribute("usuario"); 
        }
        return null;
    }

}