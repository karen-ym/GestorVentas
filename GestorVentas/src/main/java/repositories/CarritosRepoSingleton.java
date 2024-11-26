package repositories;

import java.util.ArrayList;
import java.util.List;

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
	public boolean comprarCarrito(int idUsuario) {
		return this.listaCarritos.removeIf(a -> a.getIdUsuario() == idUsuario);
	}
	
	
	
}
