package repositories.interfaces;

import java.util.List;

import models.Usuario;
import models.Venta;

public interface VentasRepo {
	// CODIGO COPIADO DEL PROFE (su ejemplo era con empleados!)

	public List<Usuario> getAll();
	public Usuario findById(int id);

	public void insert(Venta venta);
	public void delete(int id); // agrego id a venta
	// update no lo pongo porque no se actualizan las ventas
}
