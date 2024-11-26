package repositories;

import java.util.ArrayList;
import java.util.List;
import models.Venta;
import repositories.interfaces.VentasRepo;

public class VentasRepoSingleton implements VentasRepo {

    private static VentasRepoSingleton instancia;
    private List<Venta> ventas;

    private VentasRepoSingleton() {
        ventas = new ArrayList<>();
    }

    public static synchronized VentasRepoSingleton getInstance() {
        if (instancia == null) {
            instancia = new VentasRepoSingleton();
        }
        return instancia;
    }

    @Override
    public List<Venta> getAll() {
        return ventas != null ? new ArrayList<>(ventas) : new ArrayList<>();
    }

    @Override
    public Venta findById(int id) {
        return ventas.stream()
                     .filter(venta -> venta.getIdVenta() == id)
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public void insert(Venta venta) {
        ventas.add(venta);
    }

    @Override
    public void delete(int id) {
        ventas.removeIf(venta -> venta.getIdVenta() == id);
    }
}