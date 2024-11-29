package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Transaccion;
import repositories.interfaces.TransaccionesRepo;

/**
 * Servlet implementation class TransaccionesController
 */
@WebServlet("/TransaccionesController")
public class TransaccionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransaccionesRepo transaccionesRepo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransaccionesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idDeudor = Integer.parseInt(request.getParameter("idDeudor"));
            int idBeneficiario = Integer.parseInt(request.getParameter("idBeneficiario"));
            double monto = Double.parseDouble(request.getParameter("monto"));

            Transaccion nuevaTransaccion = new Transaccion(0, idDeudor, idBeneficiario, monto);
            transaccionesRepo.registrarTransaccion(nuevaTransaccion);

            response.getWriter().write("Transacción registrada exitosamente");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error al registrar transacción: " + e.getMessage());
        }
    }

}
