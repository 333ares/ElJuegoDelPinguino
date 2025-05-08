package modelo;

public abstract class Casilla { // Esta clase es abstracta porque la utilizamos como base para las clases Oso,
								// Trineo, Agujero...

	/*
	 * La clase Casilla es una clase abstracta que sirve como base para otras clases
	 * que representan tipos específicos de casillas en un tablero de juego.
	 * Proporciona un atributo posicion y métodos para acceder y modificar este
	 * atributo. Además, define un método abstracto realizarAccion que debe ser
	 * implementado por cada subclase para definir la acción específica que ocurre
	 * cuando un jugador cae en esa casilla.
	 */

	int posicion;

	// CONSTRUCTOR

	public Casilla(int posicion) {
		this.posicion = posicion;
	}

	// GETTERS Y SETTERS
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	// FUNCIONES
	public abstract void realizarAccion(Jugador j);

}
