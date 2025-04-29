package modelo;

import controlador.GestorJugador;

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

	public void tirarDado(int maximoDado) {

	}

	public void moverPosicion(int nuevaPosicion) {
		// El if se utiliza para asegurar que la nueva posición del jugador esté dentro de los límites del tablero (0 a 49, ya que hay 50 casillas).
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {
			this.posicion = nuevaPosicion;
		}
		
	}


}
