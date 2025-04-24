package modelo;

public abstract class Casilla { // Esta clase es abstracta porque la utilizamos como base para las clases Oso,
								// Trineo, Agujero...

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
