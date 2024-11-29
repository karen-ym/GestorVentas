package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Articulo;
import models.Venta;
import repositories.interfaces.ArticulosRepo;
import repositories.interfaces.VentasRepo;
import repositories.VentasRepoSingleton;

@WebServlet("/VentasController")
public class VentasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VentasRepo ventasRepo;
    private ArticulosRepo articulosRepo;
       
    public VentasController() {
        this.ventasRepo = VentasRepoSingleton.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Optional.ofNullable(request.getParameter("accion")).orElse("Historial");

        switch (accion) {
	        case "Historial" -> getHistorial(request, response);
	        case "DetalleVenta" -> getDetalle(request, response);
			default -> response.sendError(404);
        }
    }

    private void getHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Venta> ventas = ventasRepo.getAll();
        request.setAttribute("listaVentas", ventas);
        request.getRequestDispatcher("/views/Ventas/Historial.jsp").forward(request, response);
    }

    private void getDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sId = request.getParameter("id");
        try {
            int id = Integer.parseInt(sId);
            Venta venta = ventasRepo.findById(id);
            if (venta == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada con ID: " + id);
                return;
            }
            request.setAttribute("venta", venta);
            request.getRequestDispatcher("/views/Ventas/DetalleVenta.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de venta inv�lido");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
            return;
        }

        switch (accion) {
	        case "insert" -> postInsert(request, response);
	        case "delete" -> postDelete(request, response);
			default -> response.sendError(404);
        }
    }

    private void postInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cliente = request.getParameter("cliente");
        String[] codigosArticulos = request.getParameterValues("articulosSeleccionados"); // Array de códigos de artículos

        if (cliente == null || cliente.isEmpty() || codigosArticulos == null || codigosArticulos.length == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos");
            return;
        }

        try {
            List<Articulo> articulosSeleccionados = new ArrayList<>();
            for (String codigoStr : codigosArticulos) {
                int codigo = Integer.parseInt(codigoStr);
                Articulo articulo = articulosRepo.findByCodigo(codigo);
                if (articulo != null) {
                    articulosSeleccionados.add(articulo);
                }
            }

            if (articulosSeleccionados.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se encontraron artículos validos");
                return;
            }

            double total = articulosSeleccionados.stream().mapToDouble(Articulo::getPrecio).sum();
            LocalDate fechaVenta = LocalDate.now();
            Venta nuevaVenta = new Venta(0, cliente, total, articulosSeleccionados, fechaVenta);
            
            ventasRepo.insert(nuevaVenta);
            response.sendRedirect("VentasController?accion=Historial");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Codigo de artículo invalido");
        }
    }

    private void postDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sId = request.getParameter("id");
        try {
            int id = Integer.parseInt(sId);
            ventasRepo.delete(id);
            response.sendRedirect("VentasController?accion=historial");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inv�lido para eliminaci�n");
        }
    }
}