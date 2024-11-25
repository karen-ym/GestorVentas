package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Articulo;
import models.Carrito;
import repositories.interfaces.CarritosRepo;

public class CarritosRepoSingleton implements CarritosRepo{

private static CarritosRepoSingleton singleton;
	
	public static CarritosRepoSingleton getInstance() {
		if(singleton == null) {
			singleton = new CarritosRepoSingleton();
		}
		return singleton;
	}
	
	private List<Carrito> listaCarritos;
	
	private CarritosRepoSingleton() {
		this.listaCarritos = new ArrayList<Carrito>();	
	}


	@Override
	public boolean insert(int idUsuario, Articulo articulo) {	
		Carrito carrito = this.obtenerCarrito(idUsuario);
		return carrito.addCarrito(articulo);
	}
	  
	@Override
	public Carrito obtenerCarrito(int idUsuario) {	
		Carrito carrito = listaCarritos.stream()
		            .filter(a -> a.getIdUsuario() == idUsuario)
		            .findFirst()
		            .orElseGet(() -> {
		                Carrito nuevoCarrito = new Carrito(idUsuario);
		                listaCarritos.add(nuevoCarrito);
		                return nuevoCarrito;
		            });
		return carrito;
	}

	@Override
	public boolean delete(int idUsuario, int idProducto) {
		Carrito carrito = this.obtenerCarrito(idUsuario);
		return carrito.delete(idProducto);
	}

	@Override
	public double precioTotal(int idUsuario) {
		double total = this.listaCarritos.stream().filter(a -> a.getIdUsuario() == idUsuario)
				.mapToDouble(Carrito::precioTotal).sum();
		return total;
	}

	@Override
	public boolean comprarCarrito(int idUsuario) {
		//Aca se agregara la funcion para poder registrar la venta
		this.listaCarritos.remove(idUsuario);
		return false;
	}
}
