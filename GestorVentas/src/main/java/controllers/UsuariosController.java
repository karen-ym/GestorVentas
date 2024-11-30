package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Articulo;
import models.Usuario;
import models.Venta;
import repositories.ArticulosRepoSingleton;
import repositories.UsuariosRepoSingleton;
import repositories.VentasRepoSingleton;
import repositories.interfaces.ArticulosRepo;
import repositories.interfaces.UsuariosRepo;
import repositories.interfaces.VentasRepo;
import utils.Encryptor;

@WebServlet("/usuarios")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuariosRepo usuariosRepo;
	private ArticulosRepo articulosRepo;
	private VentasRepo ventasRepo;
       
    public UsuariosController() {
    		this.usuariosRepo = UsuariosRepoSingleton.getInstance();
        this.articulosRepo = ArticulosRepoSingleton.getInstance();
        this.ventasRepo = VentasRepoSingleton.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("index");
		
		switch (accion) {
		case "index"-> getIndex(request, response);
		case "show"-> getShow(request, response);
		case "create"-> getCreate(request, response);
		default -> response.sendError(404);
		}
	}

	private void getCreate(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getRequestDispatcher("views/Usuarios/Create.jsp").forward(request, response);
		

	}


	private void getShow(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sId = request.getParameter("id");
		int id = Integer.parseInt(sId);
		
		Usuario usu = usuariosRepo.findById(id);
		
		request.setAttribute("usuario", usu);
		
		request.getRequestDispatcher("views/Usuarios/show.jsp").forward(request, response);

	}

	private void getIndex(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		UsuariosRepo repo = UsuariosRepoSingleton.getInstance();
		
		List<Usuario> listaUsuarios = repo.getAll();
		
		request.setAttribute("listarda", listaUsuarios);
		
		request.getRequestDispatcher("/views/Usuarios/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("insert");
		
		switch (accion) {
		case "insert" -> postInsert(request, response);
		case "delete" -> postDelete(request, response);
		case "registrar" -> postInsert(request, response); // agrego caso para mandarlo al login -k
		default -> response.sendError(404, "No existe accion: "+accion);
		}
		
	}
	

	private void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("id");
		int id = Integer.parseInt(sId);
		
		usuariosRepo.delete(id);
		
		List<Usuario> listaUsuario = usuariosRepo.getAll();
        List<Venta> listaVenta = ventasRepo.getAll(); 
        List<Articulo> listaArticulos = articulosRepo.getAll();
        
        request.setAttribute("listarda", listaUsuario);
        request.setAttribute("listaVentas", listaVenta);
        request.setAttribute("listaArticulos", listaArticulos);
		
		request.getRequestDispatcher("/views/home/adminIndex.jsp").forward(request, response);
	}

	private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String nombreUsuario = request.getParameter("nombreUsuario");
		
		String contrasenia = request.getParameter("contrasenia");
		String contraseniaEncriptada = Encryptor.encryptMD5(contrasenia);
		
		String sSaldoActual = request.getParameter("saldoActual");
		double saldo =Double.parseDouble(sSaldoActual);
		
		String tipo = request.getParameter("tipo");
		
		Usuario usu = new Usuario(nombreUsuario, contraseniaEncriptada, tipo, saldo); 
	
		usuariosRepo.insert(usu);
		
		response.sendRedirect(request.getContextPath() + "/auth"); // cambio

}
}
