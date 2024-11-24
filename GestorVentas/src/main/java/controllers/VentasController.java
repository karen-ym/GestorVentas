package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Venta;
import repositories.interfaces.VentasRepo;
import repositories.VentasRepoSingleton;


@WebServlet("/VentasController")
public class VentasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VentasRepo ventasRepo;
       
	public VentasController() {
        this.ventasRepo = VentasRepoSingleton.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Optional.ofNullable(request.getParameter("accion")).orElse("historial");

        switch (accion) {
            case "historial":
                getHistorial(request, response);
                break;
            case "detalle":
                getDetalle(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Acción no encontrada: " + accion);
                break;
        }
    }

    private void getHistorial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Venta> ventas = ventasRepo.getAll();
        request.setAttribute("listaVentas", ventas);
        request.getRequestDispatcher("/views/ventas/historial.jsp").forward(request, response);
    }

    private void getDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sId = request.getParameter("id");
        int id = Integer.parseInt(sId);

        Venta venta = ventasRepo.findById(id);
        if (venta == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Venta no encontrada con ID: " + id);
            return;
        }

        request.setAttribute("venta", venta);
        request.getRequestDispatcher("/views/ventas/detalle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada");
            return;
        }

        switch (accion) {
            case "delete":
                postDelete(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Acción POST no encontrada: " + accion);
                break;
        }
    }

    private void postDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sId = request.getParameter("id");
        int id = Integer.parseInt(sId);

        ventasRepo.delete(id);
        response.sendRedirect("ventas?accion=historial");
    }
}