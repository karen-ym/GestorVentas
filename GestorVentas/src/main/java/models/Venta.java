package models;

import java.time.LocalDate;
import java.util.List;

public class Venta {

    private int idVenta;
    private String nombreUsuario;
    private double total;
    private List<Stock> articulos;
    private LocalDate fechaVenta;

    public Venta() {
        super();
    }

    public Venta(int id, String nombreUsuario, double total, List<Stock> articulos, LocalDate fechaVenta) {
        this.idVenta = id;
        this.nombreUsuario = nombreUsuario;
        this.total = total;
        this.articulos = articulos;
        this.fechaVenta = fechaVenta;
    }

    public double calcularTotal() {
        double total = 0;
        if (articulos != null) {
            for (Stock articulo : articulos) {
                total += articulo.getPrecio() * articulo.getCantidad(); // Suponiendo que Stock tiene estos métodos
            }
        }
        return total;
    }

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
        this.total = calcularTotal();
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }
    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
