package modelo;

import java.util.ArrayList;

public class Tablero {
	ArrayList<Casilla> casillas = new ArrayList<Casilla>();
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	int turnos;
	Jugador jugadorActual;

	// CONSTRUCTOR
	public Tablero(ArrayList<Casilla> casillas, ArrayList<Jugador> jugadores, int turnos, Jugador jugadorActual) {
		this.casillas = casillas;
		this.jugadores = jugadores;
		this.turnos = turnos;
		this.jugadorActual = jugadorActual;
	}

	// GETTERS Y SETTERS
	public ArrayList<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getTurnos() {
		return turnos;
	}

	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	// FUNCIONES
	public void actualizarTablero() {
		
	}

	public void actualizarjugador(Jugador j) {

	}
}
