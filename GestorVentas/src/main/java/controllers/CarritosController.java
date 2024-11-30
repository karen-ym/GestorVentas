package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Articulo;
import repositories.ArticulosRepoSingleton;
import repositories.CarritosRepoSingleton;
import repositories.VentasRepoSingleton;
import repositories.interfaces.ArticulosRepo;
import repositories.interfaces.CarritosRepo;
import repositories.interfaces.VentasRepo;

/**
 * Servlet implementation class CarritosController
 */
@WebServlet("/Carritos")
public class CarritosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private CarritosRepo carritosRepo;
	private ArticulosRepo articulosRepo;
	private VentasRepo ventasRepo;
	
    public CarritosController() {
        super();
        this.carritosRepo = CarritosRepoSingleton.getInstance();
        this.articulosRepo = ArticulosRepoSingleton.getInstance();
        this.ventasRepo = VentasRepoSingleton.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("index");
		
		switch (accion) {
		case "index" -> getIndex(request,response);
		case "add" -> getAdd(request,response);
		case "carrito" -> getCarrito(request,response);
		case "show" -> getShow(request,response);
		case "edit" -> getEdit(request,response);
		default ->response.sendError(404);
		}
	}


	private void getAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String sId = request.getParameter("idUsuario");
		String cId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigo = Integer.parseInt(cId);
		
		Articulo articulo = articulosRepo.findByCodigo(codigo);
		
		if(articulo == null) {
			 response.sendError(404, "Artículo no encontrado"); 
	            return;
		}
		
		request.setAttribute("articulo", articulo);
		request.setAttribute("idUsuario", idUsuario);
		
		request.getRequestDispatcher("/views/carritos/add.jsp").forward(request, response);
	}

	//Funcion que muestra una lista de articulos para comprar(de prueba)
	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("IdUsuario");
		int idUsuario = Integer.parseInt(sId);
		
		List<Articulo> articulos = articulosRepo.getAll();
		request.setAttribute("idUsuario", idUsuario);
		request.setAttribute("articulos",articulos );
		
		request.getRequestDispatcher("/views/carritos/index.jsp").forward(request, response);
	}

	//Muestra los detalles de un articulo particular
	private void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("idUsuario");
		String cId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigo = Integer.parseInt(cId);
		
		Articulo articulo = carritosRepo.findByIdArticulo(idUsuario, codigo);
		 if (articulo == null) {
	            response.sendError(404, "Artículo no encontrado"); 
	            return;
	        }
		
		request.setAttribute("articulo", articulo);
		request.getRequestDispatcher("/views/carritos/show.jsp").forward(request, response);
	}

	// Muestra los articulos del Carrito
	private void getCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("idUsuario");
		int idUsuario = Integer.parseInt(sId);
		
		List<Articulo> articulos = carritosRepo.getAll(idUsuario);
		articulos = articulos != null ? articulos : new ArrayList<>();
		double precioTotal = carritosRepo.precioTotal(idUsuario);
		
		request.setAttribute("articulos", articulos);
		request.setAttribute("precioTotal", precioTotal);
		request.setAttribute("idUsuario", sId);
		request.getRequestDispatcher("/views/carritos/carrito.jsp").forward(request, response);
	}
	
	//Muestra el articulo a editar del Carrito
	private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("idUsuario");
		String cId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigo = Integer.parseInt(cId);
		
		Articulo articulo = carritosRepo.findByIdArticulo(idUsuario, codigo);
		
		request.setAttribute("idUsuario",idUsuario );
		request.setAttribute("articulo",articulo );
		request.getRequestDispatcher("/views/carritos/edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		if(accion == null) {
			response.sendError(400,"No se brindo una accion");
			return;
		}
		
		switch (accion) {
		case "insert" -> postInsert(request,response);
		case "update" -> postUpdate(request,response);
		case "delete" -> PostDelete(request,response);
		case "comprar" -> PostComprar(request,response);
		default ->response.sendError(404); 
		}
	}

	//funcion para confirmar la compra
	private void PostComprar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("idUsuario");
	
		int idUsuario = Integer.parseInt(sId);

		carritosRepo.comprarCarrito(idUsuario);
		
		response.sendRedirect("Carritos");
	}

	// Funcion para borrar un articulo
	private void PostDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("idUsuario");
		String aId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigo = Integer.parseInt(aId);
		
		carritosRepo.delete(idUsuario, codigo);
		
		response.sendRedirect("Carritos");
	
		
	}

	//Funcion para Agregar un articulo
	private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int codigo = Integer.parseInt(request.getParameter("codigo"));
	    String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("descripcion");
	    double precio = Double.parseDouble(request.getParameter("precio"));
	    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		
		Articulo articulo = new Articulo(codigo, nombre, descripcion, precio, cantidad);
		
		carritosRepo.add(idUsuario, articulo);
		response.sendRedirect("Carritos");
		
		
	}

	//Funcion para actualizar la cantidad de articulos que se quiere comprar
	private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			
		
		if(carritosRepo.edit(idUsuario, codigo, cantidad)) {
			response.sendRedirect("Carritos");
		}else {
			response.sendError(404,"No se encontro el articulo");
		}
		
	}

}
