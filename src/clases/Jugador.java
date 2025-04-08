package clases;

public abstract class Jugador extends GestorJugador {

	int posicion;
	String nombre;
	String color;

	// CONSTRUCTOR
	public Jugador(int posicion, String nombre, String color) {
		super();
		this.posicion = posicion;
		this.nombre = nombre;
		this.color = color;
	}

	// GETTERS Y SETTERS
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// FUNCIONES

	public void aplastarJugador(Pinguino p) {

	}

	public void golpearJugador(Pinguino p) {

	}

	public void esSobornado() {

	}

}
