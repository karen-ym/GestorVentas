package repositories.interfaces;

import models.Articulo;
import models.Carrito;

public interface CarritosRepo {
	
	public boolean insert(int idUsuario, Articulo articulo);
	
	public Carrito obtenerCarrito(int id);
	
	public boolean delete(int idUsuario, int idProducto);
	
	public double precioTotal(int idUsuario);
	
	public boolean comprarCarrito(int idUsuario);
}
