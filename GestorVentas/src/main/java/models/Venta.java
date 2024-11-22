package models;

import java.util.List;

public class Venta {

	// De carrito pasa a venta
	
	// ATRIBUTOS
	private int id;
	private String nombreUsuario;
	private double total;
	private List <Stock> articulos;
	
	// CONSTRUCTOR 
	public Venta(int id, String nombreUsuario, double total, List<Stock> articulos) {
		super();
		this.id = id; // esto creeeo que había dicho que no iba
		this.nombreUsuario = nombreUsuario;
		this.total = total;
		this.articulos = articulos;
	}
	
	// SETTERS & GETTERS
	public int getIdVenta() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<Stock> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<Stock> articulos) {
		this.articulos = articulos;
	}
}
