package models;

public class VentaArticulo {
    private Articulo articulo;
    private int cantidad;      

    public VentaArticulo(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public double calcularSubtotal() {
        return articulo.getPrecio() * cantidad;
    }
}
