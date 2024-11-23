package models;

import java.util.List;

public class Venta {

	// ATRIBUTOS
	private int idVenta;
	private String nombreUsuario;
	private double total;
	private List <Stock> articulos;
	
	// CONSTRUCTOR 
	public Venta(int id, String nombreUsuario, double total, List<Stock> articulos) {
		super();
		this.idVenta = id;
		this.nombreUsuario = nombreUsuario;
		this.total = total;
		this.articulos = articulos;
	}
	
	//METODO PARA CALCULAR TOTAL
	/*private double calcularTotal() {
        double total = 0;
        for (Stock articulo : articulos) {
            total += articulos.getPrecio * articulo.getCantidad();
        }
        return total;
    }*/
	
	// SETTERS & GETTERS
	public int getIdVenta() {
		return idVenta;
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
