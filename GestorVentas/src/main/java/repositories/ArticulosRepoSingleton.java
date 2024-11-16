package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Articulo;
import repositories.interfaces.ArticulosRepo;

public class ArticulosRepoSingleton implements ArticulosRepo{

	// Para convertir la clase en singleton
	private static ArticulosRepoSingleton singleton;
	
	// Obtenemos instancia porq los singleton no E hasta que no se los llama
	public static ArticulosRepoSingleton getInstance() {
        if (singleton == null) {
            singleton = new ArticulosRepoSingleton();
        }
        return singleton;
    }
	
	private List<Articulo> listaArticulos;
	
	// El contructor va privado!!
	private ArticulosRepoSingleton() {
        this.listaArticulos = new ArrayList<>();
        
        Articulo art1 = new Articulo(9274, "Lapicera", "Aqui va description de la lapicera", 40, 100);
        
        this.insert(art1);
    }
	
	// Voy a adaptar los métodos del profe
	// TODO: hacerlo en Usuario y Venta repo después
	
	// Recordar que ArtRepo tiene: getAll, findByCodigo, insert, update, delete
	// Como hereda dichas declaraciones, las tiene que implementar!
	
	@Override
    public List<Articulo> getAll() {
        return new ArrayList<>(listaArticulos); // Devuelve una copia
    }

    @Override
    public Articulo findByCodigo(int codigo) {
        return this.listaArticulos.stream()
                .filter(art -> art.getCodigo() == codigo)
                .findAny()
                .orElse(null);
    }
	
	// TODO: insertar articulo, se hace distinto a ej
    @Override
    public void insert (Articulo articulo) {
    	// codigo
    }
    
    @Override
    public void update (Articulo articulo) {
    	// TODO
    }
    
    @Override
    public void delete(int codigo) {
    	this.listaArticulos.removeIf((art) -> art.getCodigo() == codigo);
    }
}
