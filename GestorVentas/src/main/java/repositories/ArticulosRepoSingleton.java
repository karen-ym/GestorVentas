package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Articulo;
import repositories.interfaces.ArticulosRepo;

public class ArticulosRepoSingleton implements ArticulosRepo{

	// Para convertir la clase en singleton -K
	private static ArticulosRepoSingleton singleton;
	
	// Obtenemos instancia porq los singleton no E hasta que no se los llama -K
	public static ArticulosRepoSingleton getInstance() {
        if (singleton == null) {
            singleton = new ArticulosRepoSingleton();
        }
        return singleton;
    }
	
	private List<Articulo> listaArticulos;
	
	// El contructor va privado -K
	private ArticulosRepoSingleton() {
        this.listaArticulos = new ArrayList<>();
        
        Articulo art1 = new Articulo(1, "Lapicera", "Lapicera a tinta de color rojo", 40, 100);
        this.insert(art1);
        
        Articulo art2 = new Articulo(2, "Cuaderno", "Cuaderno de 100 hojas, tapa dura", 120, 150);
        this.insert(art2);

        Articulo art3 = new Articulo(3, "Borrador", "Borrador de goma para lápiz", 15, 200);
        this.insert(art3);

        Articulo art4 = new Articulo(4, "Regla", "Regla de plástico transparente de 30 cm", 25, 120);
        this.insert(art4);

        Articulo art5 = new Articulo(5, "Tijeras", "Tijeras de acero inoxidable para oficina", 50, 75);
        this.insert(art5);

        Articulo art6 = new Articulo(6, "Carpeta", "Carpeta de cartón con 2 argollas", 70, 50);
        this.insert(art6);
    }
	
	// Recordar que ArtRepo tiene: getAll, findByCodigo, insert, update, delete
	// Como hereda dichas declaraciones, las tiene que implementar! -K
	
	@Override
    public List<Articulo> getAll() {
        return new ArrayList<>(listaArticulos); 
    }

    @Override
    public Articulo findByCodigo(int codigo) {
        return listaArticulos.stream()
            .filter(a -> a.getCodigo() == codigo)
            .findFirst()
            .orElse(null);
    }
	
	@Override
    public void insert (Articulo articulo) {
    	// Esa parte es para generar código si se necesita -K
        int ultimoCodigo = this.listaArticulos.stream()
                    .map(Articulo::getCodigo)
                    .max((a1, a2) -> Integer.compare(a1, a2))
                    .orElse(0);
        
        articulo.setCodigo(ultimoCodigo + 1);
        
        // Añadir art:
        this.listaArticulos.add(articulo);
    }
    
	@Override
	public void update(Articulo articulo) {
	    listaArticulos = listaArticulos.stream()
	        .map(a -> a.getCodigo() == articulo.getCodigo() ? articulo : a)
	        .toList();
	}
    
	@Override
	public void delete(int codigo) {
	    listaArticulos = listaArticulos.stream()
	        .filter(a -> a.getCodigo() != codigo)
	        .toList();
	}

}
