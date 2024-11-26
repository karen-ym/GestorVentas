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
import models.Carrito;
import repositories.ArticulosRepoSingleton;
import repositories.CarritosRepoSingleton;
import repositories.interfaces.ArticulosRepo;
import repositories.interfaces.CarritosRepo;

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
	
    public CarritosController() {
        super();
        this.carritosRepo = CarritosRepoSingleton.getInstance();
        this.articulosRepo = ArticulosRepoSingleton.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("carrito");
		
		switch (accion) {
		case "index" -> getIndex(request,response);
		case "carrito" -> getCarrito(request,response);
		case "add" -> getAdd(request,response);
		case "edit" -> getEdit(request,response);
		default ->response.sendError(404);
		}
	}

	//Funcion que muestra una lista de articulos para comprar(de prueba)
	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("idUsuario");
		int idUsuario = Integer.parseInt(sId);
		
		List<Articulo> articulos = articulosRepo.getAll();
		request.setAttribute("idUsuario",idUsuario );
		request.setAttribute("articulos",articulos );
		
		request.getRequestDispatcher("/views/carritos/index.jsp").forward(request, response);
	}

	// Funcion que muestra el carrito del usuario
	private void getCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrito carrito = carritosRepo.obtenerCarrito(1);
		List<Articulo> articulos = carrito.getCarrito();	
		double precioTotal = carrito.precioTotalCarrito();
		
		request.setAttribute("articulos", articulos);
		request.setAttribute("precioTotal", precioTotal);
		request.setAttribute("idUsuario", 1);
		request.getRequestDispatcher("/views/carritos/carrito.jsp").forward(request, response);
	}
	//Muestra el producto a editar
	private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sId = request.getParameter("idUsuario");//aca iria el id del usuario
		String cId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigo = Integer.parseInt(cId);
		
		Carrito carritoBuscado = carritosRepo.obtenerCarrito(codigo);
		Articulo articulo = carritoBuscado.getArticulo(codigo);
		
		request.setAttribute("idUsuario",idUsuario );
		request.setAttribute("articulo",articulo );
		request.getRequestDispatcher("/views/carritos/edit.jsp").forward(request, response);
	}

	//Muestra el articulo que se quiere comprar
	private void getAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cId = request.getParameter("codigo");
		String uId = request.getParameter("idUsuario");
		
		int idUsuario = Integer.parseInt(uId);
		int codigo = Integer.parseInt(cId);
		
		Articulo articulo = articulosRepo.findByCodigo(codigo);
		
		request.setAttribute("idUsuario",idUsuario );
		request.setAttribute("articulo",articulo );
		
		request.getRequestDispatcher("/views/carritos/add.jsp").forward(request, response);
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
		response.sendError(400,"No implementado");
	}

	//funcion para confirmar la compra
	private void PostComprar(HttpServletRequest request, HttpServletResponse response) {
		String sId = request.getParameter("idUsuario");
	
		int idUsuario = Integer.parseInt(sId);

		carritosRepo.comprarCarrito(idUsuario);
	}

	// Funcion para borrar un articulo
	private void PostDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("idUsuario");
		String aId = request.getParameter("codigo");
		
		int idUsuario = Integer.parseInt(sId);
		int codigoArticulo = Integer.parseInt(aId);
		
		Carrito carrito = carritosRepo.obtenerCarrito(idUsuario);
		carrito.deleteArticulo(codigoArticulo);
		
		
		response.sendRedirect("Carritos");
	}

	//funcion para Agregar un articulo
	private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("idUsuario");
		String aId = request.getParameter("codigo");
		String cId = request.getParameter("cantidad");
		
		int idUsuario = Integer.parseInt(sId);
		int codigoArticulo = Integer.parseInt(aId);
		int cantidad = Integer.parseInt(cId);
		
		Articulo articulo = articulosRepo.findByCodigo(codigoArticulo);
		articulo.setStock(cantidad);
		
		Carrito carrito = carritosRepo.obtenerCarrito(idUsuario);
		carrito.addCarrito(articulo);
		
		
		response.sendRedirect("Carritos");
		
		
	}

	//funcion para actualizar la cantidad de articulos
	private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sId = request.getParameter("idUsuario");
		String aId = request.getParameter("codigo");
		String cId = request.getParameter("cantidad");
		
		int idUsuario = Integer.parseInt(sId);
		int codigoArticulo = Integer.parseInt(aId);
		int cantidad = Integer.parseInt(cId);
		
		Carrito carrito = carritosRepo.obtenerCarrito(idUsuario);
		carrito.editarArticulo(codigoArticulo, cantidad);
		
		response.sendRedirect("Carritos");
		
	}

}
