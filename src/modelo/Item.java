package modelo;

public class Item {

	String nombre;
	int cantidad;

	/*
	 * Clase simple que representa un ítem con nombre y cantidad. Tiene constructor
	 * para inicializar estos valores y getters/setters para acceder/modificarlos.
	 * Es la unidad básica gestionada por la clase Inventario.
	 */

	// CONSTRUCTOR
	public Item(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	// GETTERS Y SETTERS
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
