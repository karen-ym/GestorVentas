package models;


import java.util.ArrayList;
import java.util.List;

public class Carrito {
	// ATRIBUTOS
	private int idUsuario;
    private List<Articulo> listaArticulos;

    // CONSTRUCTOR
    public Carrito(int idusuario) {
        this.listaArticulos = new ArrayList<Articulo>();
        this.idUsuario = idusuario;
    } 
    
    public Carrito(int idUsuario, ArrayList<Articulo> articulos) {
    	this.idUsuario = idUsuario;
    	this.listaArticulos = articulos;
	}
    // Debería añadir métodos para el carrito, onda agregar art y eso después
    
    public List<Articulo> getCarrito() {
		return new ArrayList<Articulo>(listaArticulos);
	}

	public boolean addCarrito(Articulo articulo) {
		boolean resultado = this.listaArticulos.add(articulo);
		return resultado;
	}
	
	public Articulo getArticulo(int codigo) {
		return this.listaArticulos.stream().filter(a -> a.getCodigo() == codigo).findFirst().orElse(null);
	}
	
	public boolean deleteArticulo(int id) {
		return listaArticulos.removeIf(a -> a.getCodigo() == id);
	}
	
	public double precioTotalCarrito() {
		return this.listaArticulos.stream().mapToDouble(Articulo::getPrecio).sum();
	}
	
	public int cantArticulos() {
		return this.listaArticulos.stream().mapToInt(Articulo::getStock).sum();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idCarrtio) {
		this.idUsuario = idCarrtio;
	}
	
	public void editarArticulo(int codigo, int cantidad) {
		Articulo articulo = this.listaArticulos.stream().filter(a -> a.getCodigo() == codigo).findFirst().orElse(null);
		articulo.setStock(cantidad);
	}
	
	@Override
	public String toString() {
		return "Carrito [idUsuario=" + idUsuario + ", listaArticulos=" + listaArticulos + "]";
	}
}
