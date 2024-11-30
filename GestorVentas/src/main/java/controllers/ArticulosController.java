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

//@WebServlet("/ArticulosController")
@WebServlet("/articulos") // acá el webservlet apunta a la url que vamos a utilizar -k
public class ArticulosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticulosRepo articulosRepo;
	private UsuariosRepo usuariosRepo;
	private VentasRepo ventasRepo;

	// Constructor para inicializar el repositorio, acá se usa el singleton -K
    public ArticulosController() {
        this.articulosRepo = ArticulosRepoSingleton.getInstance();
        this.usuariosRepo = UsuariosRepoSingleton.getInstance();
        this.ventasRepo = VentasRepoSingleton.getInstance();
    }

    
 // Maneja las peticiones GET (mostrar la lista de artículos, el formulario de creación, etc.) -K
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = Optional.ofNullable(accion).orElse("index");

        switch (accion) {
        case "index":
            getIndex(request, response);
            break;
        case "show":
            getShow(request, response);
            break;
        case "edit": 
            getEdit(request, response);
            break;
        case "create":
            getCreate(request, response);
            break;
        case "volverAIndexAdmin": 
        	List<Articulo> listaArticulos = articulosRepo.getAll();
            request.setAttribute("listaArticulos", listaArticulos);
            List<Usuario> listaUsuarios = usuariosRepo.getAll(); 
            request.setAttribute("listarda", listaUsuarios); 
            List<Venta> listaVentas = ventasRepo.getAll();    
            request.setAttribute("listaVentas", listaVentas);  

            request.getRequestDispatcher("/views/home/adminIndex.jsp").forward(request, response); 
            break;
        default:
            response.sendError(404);
    }
    }
    
    
    // METODOS GET -K

    // Formulario para CREAR art
    private void getCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		request.getRequestDispatcher("/views/articulos/create.jsp").forward(request, response);
    }

    // Formulario para EDITAR el art
    private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Articulo articulo = articulosRepo.findByCodigo(codigo);

        if (articulo == null) {
            response.sendError(404, "Artículo no encontrado"); 
            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("views/articulos/edit.jsp").forward(request, response);
    }

    
    // Mostrar detalle de un art
    private void getShow(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Articulo articulo = articulosRepo.findByCodigo(codigo);
        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("views/articulos/show.jsp").forward(request, response);
    }

    
    // Va a buscar la lista de todos los articulos para mostrar en index
    private void getIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Articulo> listaArticulos = articulosRepo.getAll();
        request.setAttribute("listaArticulos", listaArticulos);
        request.getRequestDispatcher("views/articulos/index.jsp").forward(request, response);
    }

    
    // Metodos get & post (get = buscar del server; post = enviar al server)
    
    // METODOS POST -K
    
    // Maneja las peticiones POST (crear, actualizar, eliminar artículos).
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendError(400, "No se brindó una acción");
            return;
        }

        switch (accion) {
            case "insert":
                postInsert(request, response);
                break;
            case "update":
                postUpdate(request, response);
                break;
            case "delete":
                postDelete(request, response);
                break;
            default:
                response.sendError(404, "No existe la acción: " + accion);
        }
    }

    
    // Eliminar un artículo
    private void postDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        articulosRepo.delete(codigo);
        response.sendRedirect(request.getContextPath() + "/articulos?accion=volverAIndexAdmin");
    }

    
    // Actualizar un artículo 
    private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Articulo articulo = articulosRepo.findByCodigo(codigo);

        if (articulo != null) {  
            articulo.setNombre(nombre);
            articulo.setDescripcion(descripcion);
            articulo.setPrecio(precio);
            articulo.setStock(stock);
            articulosRepo.update(articulo);
        } else {
            response.sendError(404, "Artículo no encontrado");
            return; 
        }

        response.sendRedirect(request.getContextPath() + "/articulos?accion=volverAIndexAdmin");
    }

    
    // Crea un nuevo artículo
    private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio")); 
        int stock = Integer.parseInt(request.getParameter("stock")); 

        Articulo articulo = new Articulo();
        articulo.setNombre(nombre);
        articulo.setDescripcion(descripcion);
        articulo.setPrecio(precio);
        articulo.setStock(stock);

        articulosRepo.insert(articulo);

        response.sendRedirect(request.getContextPath() + "/articulos?accion=volverAIndexAdmin");
    }

}
