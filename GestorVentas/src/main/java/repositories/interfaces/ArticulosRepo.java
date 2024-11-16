package repositories.interfaces;

import java.util.List;
import models.Articulo;

public interface ArticulosRepo {
	
	// CODIGO COPIADO DEL PROFE (su ejemplo era con empleados!)
	// Aca id es código de art
	
	List<Articulo> getAll();
    Articulo findByCodigo(int codigo); 

	// CRUD:
	public void insert(Articulo articulo);
	public void update(Articulo articulo);
	public void delete(int codigo);
}
