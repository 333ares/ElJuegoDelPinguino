package clases;

import java.util.ArrayList;

public abstract class Casilla extends Tablero {

	int posicion;
	ArrayList<Jugador> jugadoresActuales = new ArrayList<Jugador>();

	// CONSTRUCTOR

	public Casilla(ArrayList<Casilla> casillas, ArrayList<Jugador> jugadores, int turnos, Jugador jugadorActual,
			int posicion, ArrayList<Jugador> jugadoresActuales) {
		super(casillas, jugadores, turnos, jugadorActual);
		this.posicion = posicion;
		this.jugadoresActuales = jugadoresActuales;
	}

	// GETTERS Y SETTERS
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public ArrayList<Jugador> getJugadoresActuales() {
		return jugadoresActuales;
	}

	public void setJugadoresActuales(ArrayList<Jugador> jugadoresActuales) {
		this.jugadoresActuales = jugadoresActuales;
	}

	// FUNCIONES

	public void añadirJugador(Jugador j) {

	}

	public abstract void realizarAccion();

}
