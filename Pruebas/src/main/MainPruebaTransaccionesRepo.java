package main;

import models.Transaccion;
import models.Usuario;
import repositories.TransaccionesRepoSingleton;
import repositories.UsuariosRepoSingleton;

public class MainPruebaTransaccionesRepo {

	public static void main(String[] args) {
		
		UsuariosRepoSingleton usuariosRepo = UsuariosRepoSingleton.getInstance();
        TransaccionesRepoSingleton transaccionesRepo = TransaccionesRepoSingleton.getInstance();

        // Crear usuarios de prueba
        Usuario usuario1 = new Usuario("usuario1", "contraseña1", "cliente", 1000);
        Usuario usuario2 = new Usuario("usuario2", "contraseña2", "cliente", 500);

        usuariosRepo.insert(usuario1);
        usuariosRepo.insert(usuario2);

        // Registrar una transacción -K
        Transaccion transaccion = new Transaccion(0, usuario1.getId(), usuario2.getId(), 220);
        transaccionesRepo.registrarTransaccion(transaccion);

        
        // Simple print para las transaccion(ES)
        for (Transaccion t : transaccionesRepo.getAll()) {
            System.out.println("idUsuario " + t.getId() + ": paso " + t.getMontoTransaccionado() + " pesos" + " a idUsuario" + t.getIdUsuarioBeneficiario());
        }

	}

}
