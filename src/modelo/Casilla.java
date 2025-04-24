package modelo;

import java.util.ArrayList;

public abstract class Casilla { //Esta clase es abstracta porque la utilizamos como base para las clases Oso, Trineo, Agujero...

	int posicion;
	ArrayList<Jugador> jugadoresActuales = new ArrayList<Jugador>();

	// CONSTRUCTOR

	public Casilla(int posicion, ArrayList<Jugador> jugadoresActuales) {
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

	public abstract void realizarAccion(Jugador j);

}
