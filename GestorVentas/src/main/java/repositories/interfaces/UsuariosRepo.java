package repositories.interfaces;

import java.util.List;

import models.Usuario;

public interface UsuariosRepo {
	
	// CODIGO COPIADO DEL PROFE (su ejemplo era con empleados!)

	public List<Usuario> getAll();
	public Usuario findById(int id);

	// CRUD: nombra importancia para bd pero no trabajamos con bd
	public void insert(Usuario usuario);
	public void delete(int idUsuario); // como usó id se lo agrego a la entidad (me iba a manejar con nombreUsuario)
	
}
