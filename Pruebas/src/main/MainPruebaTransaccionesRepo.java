package main;

import java.util.List;

import models.Transaccion;
import models.Usuario;
import repositories.TransaccionesRepoSingleton;
import repositories.UsuariosRepoSingleton;

public class MainPruebaTransaccionesRepo {

	public static void main(String[] args) {
		
		UsuariosRepoSingleton usuariosRepo = UsuariosRepoSingleton.getInstance();
        TransaccionesRepoSingleton transaccionesRepo = TransaccionesRepoSingleton.getInstance();

        // Crear usuarios de prueba
        Usuario usuario1 = new Usuario("usuario1", "pass1", "cliente", 1000);
        Usuario usuario2 = new Usuario("usuario2", "pass2", "cliente", 500);

        usuariosRepo.insert(usuario1);
        usuariosRepo.insert(usuario2);
        
        // print: saldo inicial
        System.out.println("Saldo inicial usuario1: " + usuario1.getSaldoActual());
        System.out.println("Saldo inicial usuario2: " + usuario2.getSaldoActual());

        // Registrar una transacción -K [id, idUsuarioDeudor, idUsuarioBeneficiario, monto]
        Transaccion transaccion1 = new Transaccion(0, usuario1.getId(), usuario2.getId(), 400);
        transaccionesRepo.registrarTransaccion(transaccion1);
        
        // Registrar otra pero SIN SALDO
        try {
	        Transaccion transaccion2 = new Transaccion(0, usuario1.getId(), usuario2.getId(), 1000);
	        transaccionesRepo.registrarTransaccion(transaccion2);
        } catch (RuntimeException e){
        	System.out.println("Error: " + e.getMessage());
        };
        
        // print: saldo actual luego de transacciones
        usuario1 = usuariosRepo.findById(usuario1.getId()); 
        usuario2 = usuariosRepo.findById(usuario2.getId());
        System.out.println("Saldo usuario1 después de la transacción: " + usuario1.getSaldoActual());
        System.out.println("Saldo usuario2 después de la transacción: " + usuario2.getSaldoActual());
        
        
        // print: TODAS las transacciones
        System.out.println("\nTodas las transacciones:");
        for (Transaccion t : transaccionesRepo.getAll()) {
            System.out.println("idUsuario " + t.getId() + ": paso " + t.getMontoTransaccionado() + " pesos" + " a idUsuario" + t.getIdUsuarioBeneficiario());
        }
        
        // Todas pero POR usuario
        
        List<Transaccion> transaccionesDeUsuario1 = transaccionesRepo.obtenerTransaccionesPorUsuarioDeudor(usuario1.getId());
        List<Transaccion> transaccionesAUsuario2 = transaccionesRepo.obtenerTransaccionesPorUsuarioBeneficiario(usuario2.getId());
        
        System.out.println("\nTransacciones de usuario1 (deudor):");
        transaccionesDeUsuario1.forEach(t -> System.out.println(t.getId() + ": " + t.getMontoTransaccionado()));

        System.out.println("\nTransacciones a usuario2 (beneficiario):");
        transaccionesAUsuario2.forEach(t -> System.out.println(t.getId() + ": " + t.getMontoTransaccionado()));

	}

}
