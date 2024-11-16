package main;

import java.util.List;

import models.Usuario;
import repositories.UsuariosRepoSingleton;
import repositories.interfaces.UsuariosRepo;

public class MainPruebaRepo {

	public static void main(String[] args) {

		// copiado del profe, lo remplaze con usuario pero no lo probe :(
		UsuarioRepo repo = UsuariosRepoSingleton.getInstance();

		Usuario nuevo = new Usuario("Nestor", 26, 30000); // modif param
		repo.insert(nuevo);
		List<Usuario> listado = repo.getAll();

		listado.forEach(System.out::println);

		Usuario user = repo.findById(1);
		user.setSueldo(87412);

		repo.update(user);
		System.out.println("----------");

		List<Usuario> listado2 = repo.getAll();
		listado2.forEach(System.out::println);
	}
}
