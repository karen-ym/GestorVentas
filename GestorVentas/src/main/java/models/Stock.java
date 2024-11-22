package models;

public class Stock {
	
	// ATRIBUTOS
	public Articulo articulo;
    public int cantidad;

    // CONSTRUCTOR
    public Stock(Articulo articulo, int cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

    // GETTERS & SETTERS
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
