package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import repositories.interfaces.UsuariosRepo;

public class UsuariosRepoSingleton implements UsuariosRepo{
	
	private static UsuariosRepoSingleton singleton;
	
	public static UsuariosRepoSingleton getInstance() {
		if(singleton == null) {
			singleton = new UsuariosRepoSingleton();
		}
		return singleton;
	}
	
	private List<Usuario> listaUsuarios;
	
	private UsuariosRepoSingleton() {
		this.listaUsuarios = new ArrayList<Usuario>();
	}

	@Override
	public List<Usuario> getAll() {
		
		return new ArrayList<Usuario>(this.listaUsuarios);
	}

	@Override
	public Usuario findById(int id) {
		
		return this.listaUsuarios.stream()
		.filter((e)-> e.getId() == id )
		.findAny()
		.orElse(null);
	}

	@Override
	public void insert(Usuario usuario) {
		
		int ultimaId = this.listaUsuarios.stream()
				.map(Usuario::getId)
				.max(Integer::compare)
				.orElse(0);
		
		usuario.setId(ultimaId+1);
		
		this.listaUsuarios.add(usuario);
	}

	@Override
	public void delete(int id) {
		this.listaUsuarios.removeIf((u)-> u.getId() == id);
	}

	// Agrego para actualizar transacciones -K
	@Override
    public void update(Usuario usuarioActualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);
            if (usuario.getId() == usuarioActualizado.getId()) {
                listaUsuarios.set(i, usuarioActualizado); 
                return; 
            }
        }
    }

}
