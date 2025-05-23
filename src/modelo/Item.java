package modelo;

public class Item {
	private String nombre;
	private int cantidad;

	/*
	 * Representa un ítem dentro del inventario del jugador.
	 * 
	 * Contiene información sobre el tipo de ítem y la cantidad disponible.
	 */
	public Item(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}