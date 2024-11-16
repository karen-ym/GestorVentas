package models;

public class Usuario {
	
	// Como mínimo: nombre usuario y contraseña
	
	// ATRIBUTOS
	private int id;
	private String nombreUsuario; // podría ser idUsuario ? pero no se nombra el uso de bbdd
	private String contrasenia;
	private boolean admin; // true = admin | false = cliente
	private double saldoActual; // me evito entidad billetera

	// CONSTRUCTOR
	public Usuario(String nombreUsuario, String contrasenia, boolean admin, double saldoActual) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.admin = admin;
		this.saldoActual = saldoActual;
	}

	// SETTERS & GETTERS
	
	// solo un getter de id
	public int getId() {
		return id;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}
}
