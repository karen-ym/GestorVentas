package repositories.interfaces;

import models.Carrito;

public interface CarritosRepo {
	
	public Carrito obtenerCarrito(int idUsuario);
	
	public boolean comprarCarrito(int idUsuario);
}
