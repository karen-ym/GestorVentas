package main;

import java.util.List;

import models.Articulo;
import repositories.ArticulosRepoSingleton;
import repositories.interfaces.ArticulosRepo;

// Lo paso acá por si mainpruebarepo se sobreescribe -K
public class MainPruebasArticuloRepo {

	public static void main(String[] args) {

		// PRUEBAS ARTICULO -K
		ArticulosRepo repo = ArticulosRepoSingleton.getInstance();

        // Insertar un nuevo artículo [Crud]
        Articulo nuevoArticulo = new Articulo(0, "Resaltador", "Resaltador amarillo", 30, 50); // Código 0 para que se genere uno nuevo
        repo.insert(nuevoArticulo);
        System.out.println("CREATE (resaltador):");
        repo.getAll().forEach(System.out::println);
        System.out.println("------------------");

        // Buscar un artículo y modificarlo [crUd]
        Articulo artModificado = repo.findByCodigo(1);
        if (artModificado != null) {
            artModificado.setPrecio(150);
            artModificado.setStock(25); 
            repo.update(artModificado);
        }

        System.out.println("UPDATE (Lapicera cambia stock y precio):");
        repo.getAll().forEach(System.out::println);
        System.out.println("------------------");

        // Eliminar un artículo [cruD]
        repo.delete(2);
        System.out.println("DELETE (borrar cuaderno):");
        repo.getAll().forEach(System.out::println);
        System.out.println("------------------");

        // Leer lista artículos [cRud]
        System.out.println("READ (todo):");
        List<Articulo> listado = repo.getAll();
        listado.forEach(System.out::println);
	}

}
